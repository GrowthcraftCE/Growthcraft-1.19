import shutil

templateBaseString = "honey"

baseStrings = [
  "cheese_base"
]

for baseString in baseStrings:
  if baseString == templateBaseString:
    continue
  sourceFiles = [
    "../assets/growthcraft_apiary/textures/block/fluid/%s_fluid.png" % templateBaseString,
    "../assets/growthcraft_apiary/textures/block/fluid/%s_fluid_flowing.png" % templateBaseString,
    "../assets/growthcraft_apiary/textures/block/fluid/%s_fluid_flowing.png.mcmeta" % templateBaseString,
    "../assets/growthcraft_apiary/textures/block/fluid/%s_fluid_overlay.png" % templateBaseString,
    "../assets/growthcraft_apiary/textures/block/fluid/%s_fluid_still.png" % templateBaseString,
    "../assets/growthcraft_apiary/textures/block/fluid/%s_fluid_still.png.mcmeta" % templateBaseString
    ]
  targetFiles = [
    "../assets/growthcraft_milk/textures/block/fluid/%s_fluid.png" % baseString,
    "../assets/growthcraft_milk/textures/block/fluid/%s_fluid_flowing.png" % baseString,
    "../assets/growthcraft_milk/textures/block/fluid/%s_fluid_flowing.png.mcmeta" % baseString,
    "../assets/growthcraft_milk/textures/block/fluid/%s_fluid_overlay.png" % baseString,
    "../assets/growthcraft_milk/textures/block/fluid/%s_fluid_still.png" % baseString,
    "../assets/growthcraft_milk/textures/block/fluid/%s_fluid_still.png.mcmeta" % baseString
  ]
  for x in range(0, len(sourceFiles)):
    shutil.copy(sourceFiles[x], targetFiles[x])
    #with open(targetFiles[x], 'r') as file:
    #  contents = file.read()
    #  contents = contents.replace(templateBaseString, baseString)
    #with open(targetFiles[x], 'w') as file:
    #  file.write(contents)
