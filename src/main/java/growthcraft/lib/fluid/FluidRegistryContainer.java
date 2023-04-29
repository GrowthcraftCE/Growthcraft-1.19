package growthcraft.lib.fluid;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import growthcraft.lib.client.ClientFluidTypeExtensions;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FluidRegistryContainer {
    public final RegistryObject<FluidType> type;
    public final FluidType.Properties typeProperties;
    public final RegistryObject<LiquidBlock> block;
    public final RegistryObject<BucketItem> bucket;
    public final RegistryObject<ForgeFlowingFluid.Source> source;
    public final RegistryObject<ForgeFlowingFluid.Flowing> flowing;
    private ForgeFlowingFluid.Properties properties;

    public DeferredRegister<Fluid> FLUID_REGISTRY;
    public DeferredRegister<FluidType> FLUID_TYPE_REGISTRY;

    public DeferredRegister<Block> BLOCK_REGISTRY;
    public DeferredRegister<Item> ITEM_REGISTRY;

    public FluidRegistryContainer(String name,
                                  FluidType.Properties typeProperties,
                                  Supplier<IClientFluidTypeExtensions> clientExtensions,
                                  @Nullable AdditionalProperties additionalProperties,
                                  BlockBehaviour.Properties blockProperties,
                                  Item.Properties itemProperties,
                                  DeferredRegister<Fluid>  FLUID_REGISTRY,
                                  DeferredRegister<FluidType> FLUID_TYPE_REGISTRY,
                                  DeferredRegister<Block> BLOCK_REGISTRY,
                                  DeferredRegister<Item> ITEM_REGISTRY) {

        this.FLUID_REGISTRY = FLUID_REGISTRY;
        this.FLUID_TYPE_REGISTRY = FLUID_TYPE_REGISTRY;
        this.BLOCK_REGISTRY = BLOCK_REGISTRY;
        this.ITEM_REGISTRY = ITEM_REGISTRY;

        this.typeProperties = typeProperties.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY);

        this.type = FLUID_TYPE_REGISTRY.register(name, () -> new FluidType(this.typeProperties) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(clientExtensions.get());
            }
        });

        this.source = FLUID_REGISTRY.register(name + "_source", () -> new ForgeFlowingFluid.Source(this.properties));
        this.flowing = FLUID_REGISTRY.register(name + "_flowing",
                () -> new ForgeFlowingFluid.Flowing(this.properties));

        this.properties = new ForgeFlowingFluid.Properties(this.type, this.source, this.flowing);
        if (additionalProperties != null) {
            this.properties.explosionResistance(additionalProperties.explosionResistance)
                    .levelDecreasePerBlock(additionalProperties.levelDecreasePerBlock)
                    .slopeFindDistance(additionalProperties.slopeFindDistance).tickRate(additionalProperties.tickRate);
        }

        this.block = BLOCK_REGISTRY.register(name, () -> new LiquidBlock(this.source, blockProperties));
        this.properties.block(this.block);

        this.bucket = ITEM_REGISTRY.register(name + "_bucket", () -> new BucketItem(this.source, itemProperties));
        this.properties.bucket(this.bucket);
    }

    public FluidRegistryContainer(String name, FluidType.Properties typeProperties,
                                  Supplier<IClientFluidTypeExtensions> clientExtensions, BlockBehaviour.Properties blockProperties,
                                  Item.Properties itemProperties,
                                  DeferredRegister<Fluid>  FLUID_REGISTRY,
                                  DeferredRegister<FluidType> FLUID_TYPE_REGISTRY,
                                  DeferredRegister<Block> BLOCK_REGISTRY,
                                  DeferredRegister<Item> ITEM_REGISTRY) {
        this(name, typeProperties, clientExtensions, null, blockProperties, itemProperties,
                FLUID_REGISTRY, FLUID_TYPE_REGISTRY, BLOCK_REGISTRY, ITEM_REGISTRY);
    }

    public static IClientFluidTypeExtensions createExtension(ClientFluidTypeExtensions extensions) {
        return new IClientFluidTypeExtensions() {
            private static final ResourceLocation UNDERWATER_LOCATION = new ResourceLocation("textures/misc/underwater.png");
            private static final ResourceLocation WATER_STILL = new ResourceLocation("block/water_still");
            private static final ResourceLocation WATER_FLOW = new ResourceLocation("block/water_flow");
            private static final ResourceLocation WATER_OVERLAY = new ResourceLocation("block/water_overlay");

            @Override
            public ResourceLocation getFlowingTexture() {
                return WATER_FLOW;
            }

            @Nullable
            @Override
            public ResourceLocation getOverlayTexture() {
                return WATER_OVERLAY;
            }

            @Override
            public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                return UNDERWATER_LOCATION;
            }

            @Override
            public ResourceLocation getStillTexture() {
                return WATER_STILL;
            }

            @Override
            public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
                return this.getTintColor();
            }

            @Override
            public int getTintColor(FluidStack stack) {
                return this.getTintColor();
            }

            @Override
            public int getTintColor() {
                return extensions.tintColor;
            }

            @Override
            public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level,
                                                    int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return extensions.fogColor == null
                        ? IClientFluidTypeExtensions.super.modifyFogColor(camera, partialTick, level, renderDistance,
                        darkenWorldAmount, fluidFogColor)
                        : extensions.fogColor;
            }

            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick,
                                        float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart(1f);
                RenderSystem.setShaderFogEnd(6f);
            }
        };
    }

    public ForgeFlowingFluid.Properties getProperties() {
        return this.properties;
    }

    public static class AdditionalProperties {
        private int levelDecreasePerBlock = 1;
        private float explosionResistance = 1;
        private int slopeFindDistance = 4;
        private int tickRate = 5;

        public AdditionalProperties explosionResistance(float resistance) {
            this.explosionResistance = resistance;
            return this;
        }

        public AdditionalProperties levelDecreasePerBlock(int decrease) {
            this.levelDecreasePerBlock = decrease;
            return this;
        }

        public AdditionalProperties slopeFindDistance(int distance) {
            this.slopeFindDistance = distance;
            return this;
        }

        public AdditionalProperties tickRate(int rate) {
            this.tickRate = rate;
            return this;
        }
    }

}
