copy_defaults: false
crafting:
  disable: null
  enable:
    Emerald to XP:
      inputs:
        Emerald:
          material: EMERALD
      output:
        Exp Bottle:
          amount: 9
          material: EXP_BOTTLE
    Slab to Double Slab:
      inputs:
        s:
          Stone Slab:
            material: STEP
      output:
        Double Stone Slab:
          material: DOUBLE_STEP
      shape:
      - s
      - s
    Stone to Double Slab:
      inputs:
        s:
          Stone:
            material: STONE
      output:
        Double Stone Slab:
          material: DOUBLE_STEP
      shape:
      - sss
      - sss
    XP to Emerald:
      inputs:
        Exp Bottle:
          amount: 9
          material: EXP_BOTTLE
      output:
        Emerald:
          material: EMERALD
general:
  central_block: WORKBENCH
  citadel_enabled: true
  disable_experience: true
  factory_interaction_material: STICK
  return_build_materials: false
  save_cycle: 15
printing_presses:
  costs:
    binding:
      Leather:
        amount: 1
        material: LEATHER
    construction:
      Gold plate:
        amount: 20
        material: GOLD_PLATE
      Iron block:
        amount: 60
        material: IRON_BLOCK
      Piston:
        amount: 20
        material: PISTON_BASE
      Quartz:
        amount: 64
        material: QUARTZ
      Redstone:
        amount: 256
        material: REDSTONE
    page_lot:
      Ink:
        amount: 2
        durability: 0
        material: INK_SACK
      Paper:
        amount: 16
        material: PAPER
    pages_per_lot: 24
    pamphlet_lot:
      Ink:
        amount: 2
        durability: 0
        material: INK_SACK
      Paper:
        amount: 16
        material: PAPER
    pamphlets_per_lot: 24
    plates:
      Gold nugget:
        amount: 1
        material: GOLD_NUGGET
      Iron ingot:
        amount: 4
        material: IRON_INGOT
    repair:
      Iron block:
        amount: 1
        material: IRON_BLOCK
    repair_multiple: 5
    security_lot:
      Cactus green:
        amount: 6
        durability: 2
        material: INK_SACK
      Gold nuggets:
        amount: 1
        material: GOLD_NUGGET
    security_notes_per_lot: 24
  fuel:
    Charcoal:
      durability: 1
      material: COAL
production_factories:
  Advanced_Redstone_Factory:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Chest:
        amount: 72
        material: CHEST
      Glowstone:
        amount: 256
        material: GLOWSTONE
      Iron_Ingot:
        amount: 576
        material: IRON_INGOT
      Redstone:
        amount: 960
        material: REDSTONE
      Slime ball:
        amount: 128
        material: SLIME_BALL
    name: Advanced Redstone Mechanism Factory
    recipes:
    - Produce_Noteblocks
    - Produce_Dispensers
    - Produce_Redstone_lamps
    - Produce_Pistons
    - Produce_Sticky_Pistons
    repair_inputs:
      Glowstone:
        amount: 25
        material: GLOWSTONE
      Iron_Ingot:
        amount: 6
        material: IRON_INGOT
      Redstone:
        amount: 9
        material: REDSTONE
      Slime ball:
        amount: 1
        material: SLIME_BALL
    repair_multiple: 10
  Bakery:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Baked Potato:
        amount: 512
        material: BAKED_POTATO
      Bread:
        amount: 256
        material: BREAD
      Cookie:
        amount: 1024
        material: COOKIE
    name: Bakery
    recipes:
    - Baked_Potato
    - Cookie
    - Bread
    repair_inputs:
      Baked Potato:
        amount: 2
        material: BAKED_POTATO
      Bread:
        material: BREAD
      Cookie:
        amount: 4
        material: COOKIE
    repair_multiple: 26
  Basic_Redstone_Factory:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Glass:
        amount: 128
        material: GLASS
      Netherquartz:
        amount: 256
        material: QUARTZ
      Redstone:
        amount: 960
        material: REDSTONE
      Stick:
        amount: 512
        material: STICK
      Stone:
        amount: 576
        material: STONE
    name: Basic Redstone Mechanism Factory
    recipes:
    - Produce_Redstone_Torches
    - Produce_Repeaters
    - Produce_Comparators
    - Produce_Daylight_Sensors
    repair_inputs:
      Glass:
        amount: 12
        material: GLASS
      Netherquartz:
        amount: 25
        material: QUARTZ
      Redstone:
        amount: 9
        material: REDSTONE
    repair_multiple: 10
  Black_Wool_Processing:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Black Wool:
        amount: 20
        durability: 15
        material: WOOL
      Bone Meal:
        amount: 20
        durability: 15
        material: INK_SACK
      Cactus Green:
        amount: 20
        durability: 2
        material: INK_SACK
      Cocoa:
        amount: 20
        durability: 3
        material: INK_SACK
      Cyan Dye:
        amount: 20
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        amount: 20
        durability: 11
        material: INK_SACK
      Gray Dye:
        amount: 20
        durability: 8
        material: INK_SACK
      Ink Sack:
        amount: 20
        material: INK_SACK
      Lapis Lazuli:
        amount: 20
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        amount: 20
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        amount: 20
        durability: 7
        material: INK_SACK
      Lime Dye:
        amount: 20
        durability: 10
        material: INK_SACK
      Magenta Dye:
        amount: 20
        durability: 13
        material: INK_SACK
      Orange Dye:
        amount: 20
        durability: 14
        material: INK_SACK
      Pink Dye:
        amount: 20
        durability: 9
        material: INK_SACK
      Purple Dye:
        amount: 20
        durability: 5
        material: INK_SACK
      Rose Red:
        amount: 20
        durability: 1
        material: INK_SACK
    name: Black Wool Processing
    recipes:
    - Dye_Black_Wool_Blue
    - Dye_Black_Wool_Gray
    - Dye_Black_Wool_Brown
    - Dye_Black_Wool_Purple
    - Dye_Black_Wool_Yellow
    - Dye_Black_Wool_Magenta
    - Dye_Black_Wool_Pink
    - Dye_Black_Wool_Cyan
    - Dye_Black_Wool_Orange
    - Dye_Black_Wool_Green
    - Dye_Black_Wool_White
    - Dye_Black_Wool_Light_Gray
    - Dye_Black_Wool_Light_Blue
    - Dye_Black_Wool_Red
    - Dye_Black_Wool_Lime
    repair_inputs:
      Black Wool:
        durability: 15
        material: WOOL
      Bone Meal:
        durability: 15
        material: INK_SACK
      Cactus Green:
        durability: 2
        material: INK_SACK
      Cocoa:
        durability: 3
        material: INK_SACK
      Cyan Dye:
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        durability: 11
        material: INK_SACK
      Gray Dye:
        durability: 8
        material: INK_SACK
      Ink Sack:
        material: INK_SACK
      Lapis Lazuli:
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        durability: 7
        material: INK_SACK
      Lime Dye:
        durability: 10
        material: INK_SACK
      Magenta Dye:
        durability: 13
        material: INK_SACK
      Orange Dye:
        durability: 14
        material: INK_SACK
      Pink Dye:
        durability: 9
        material: INK_SACK
      Purple Dye:
        durability: 5
        material: INK_SACK
      Rose Red:
        durability: 1
        material: INK_SACK
    repair_multiple: 2
  Brown_Wool_Processing:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Bone Meal:
        amount: 20
        durability: 15
        material: INK_SACK
      Brown Wool:
        amount: 20
        durability: 12
        material: WOOL
      Cactus Green:
        amount: 20
        durability: 2
        material: INK_SACK
      Cocoa:
        amount: 20
        durability: 3
        material: INK_SACK
      Cyan Dye:
        amount: 20
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        amount: 20
        durability: 11
        material: INK_SACK
      Gray Dye:
        amount: 20
        durability: 8
        material: INK_SACK
      Ink Sack:
        amount: 20
        material: INK_SACK
      Lapis Lazuli:
        amount: 20
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        amount: 20
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        amount: 20
        durability: 7
        material: INK_SACK
      Lime Dye:
        amount: 20
        durability: 10
        material: INK_SACK
      Magenta Dye:
        amount: 20
        durability: 13
        material: INK_SACK
      Orange Dye:
        amount: 20
        durability: 14
        material: INK_SACK
      Pink Dye:
        amount: 20
        durability: 9
        material: INK_SACK
      Purple Dye:
        amount: 20
        durability: 5
        material: INK_SACK
      Rose Red:
        amount: 20
        durability: 1
        material: INK_SACK
    name: Brown Wool Processing
    recipes:
    - Dye_Brown_Wool_Blue
    - Dye_Brown_Wool_Gray
    - Dye_Brown_Wool_Purple
    - Dye_Brown_Wool_Yellow
    - Dye_Brown_Wool_Black
    - Dye_Brown_Wool_Magenta
    - Dye_Brown_Wool_Pink
    - Dye_Brown_Wool_Cyan
    - Dye_Brown_Wool_Orange
    - Dye_Brown_Wool_Green
    - Dye_Brown_Wool_White
    - Dye_Brown_Wool_Light_Gray
    - Dye_Brown_Wool_Light_Blue
    - Dye_Brown_Wool_Red
    - Dye_Brown_Wool_Lime
    repair_inputs:
      Bone Meal:
        durability: 15
        material: INK_SACK
      Brown Wool:
        durability: 12
        material: WOOL
      Cactus Green:
        durability: 2
        material: INK_SACK
      Cocoa:
        durability: 3
        material: INK_SACK
      Cyan Dye:
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        durability: 11
        material: INK_SACK
      Gray Dye:
        durability: 8
        material: INK_SACK
      Ink Sack:
        material: INK_SACK
      Lapis Lazuli:
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        durability: 7
        material: INK_SACK
      Lime Dye:
        durability: 10
        material: INK_SACK
      Magenta Dye:
        durability: 13
        material: INK_SACK
      Orange Dye:
        durability: 14
        material: INK_SACK
      Pink Dye:
        durability: 9
        material: INK_SACK
      Purple Dye:
        durability: 5
        material: INK_SACK
      Rose Red:
        durability: 1
        material: INK_SACK
    repair_multiple: 2
  Charcoal_Smelter:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Charcoal:
        amount: 600
        durability: 1
        material: COAL
    name: Charcoal Burner
    recipes:
    - Smelt_Oak_Wood
    - Smelt_Spruce_Wood
    - Smelt_Birch_Wood
    - Smelt_Jungle_Wood
    - Smelt_Coal
    repair_inputs:
      Charcoal:
        durability: 1
        material: COAL
    repair_multiple: 60
  Diamond_Axe_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Diamond:
        amount: 64
        material: DIAMOND
    name: Diamond Axe Smithy
    recipes:
    - Diamond_Axe
    repair_inputs:
      Diamond:
        material: DIAMOND
    repair_multiple: 7
  Diamond_Boots_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Diamond:
        amount: 160
        material: DIAMOND
    name: Diamond Boots Smithy
    recipes:
    - Diamond_Boots
    repair_inputs:
      Diamond:
        material: DIAMOND
    repair_multiple: 16
  Diamond_Cauldron:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Diamond:
        amount: 50
        material: DIAMOND
    name: Diamond Cauldron
    recipes:
    - Diamond_XP_Bottle_0
    - Diamond_XP_Bottle_1
    - Diamond_XP_Bottle_2
    - Diamond_XP_Bottle_3
    repair_inputs:
      Diamond:
        material: DIAMOND
    repair_multiple: 5
  Diamond_Chestplate_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Diamond:
        amount: 320
        material: DIAMOND
    name: Diamond Chestplate Smithy
    recipes:
    - Diamond_Chestplate
    repair_inputs:
      Diamond:
        material: DIAMOND
    repair_multiple: 32
  Diamond_Helmet_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Diamond:
        amount: 192
        material: DIAMOND
    name: Diamond Helmet Smithy
    recipes:
    - Diamond_Helmet
    repair_inputs:
      Diamond:
        material: DIAMOND
    repair_multiple: 20
  Diamond_Hoe_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Diamond:
        amount: 32
        material: DIAMOND
    name: Diamond Hoe Smithy
    recipes:
    - Diamond_Hoe
    repair_inputs:
      Diamond:
        material: DIAMOND
    repair_multiple: 4
  Diamond_Leggings_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Diamond:
        amount: 256
        material: DIAMOND
    name: Diamond Leggings Smithy
    recipes:
    - Diamond_Leggings
    repair_inputs:
      Diamond:
        material: DIAMOND
    repair_multiple: 26
  Diamond_Pickaxe_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Diamond:
        amount: 96
        material: DIAMOND
    name: Diamond Pickaxe Smithy
    recipes:
    - Diamond_Pickaxe
    repair_inputs:
      Diamond:
        material: DIAMOND
    repair_multiple: 10
  Diamond_Spade_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Diamond:
        amount: 48
        material: DIAMOND
    name: Diamond Spade Smithy
    recipes:
    - Diamond_Spade
    repair_inputs:
      Diamond:
        material: DIAMOND
    repair_multiple: 5
  Diamond_Sword_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Diamond:
        amount: 80
        material: DIAMOND
    name: Diamond Sword Smithy
    recipes:
    - Diamond_Sword
    repair_inputs:
      Diamond:
        material: DIAMOND
    repair_multiple: 8
  Fancy_Ore_Smelter:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Lapis Lazuli:
        amount: 1536
        durability: 4
        material: INK_SACK
      Netherquartz:
        amount: 576
        material: QUARTZ
      Redstone:
        amount: 1152
        material: REDSTONE
    name: Fancy Ore Smelter
    recipes:
    - Smelt_Lapis_Lazuli_Ore
    - Smelt_Redstone_Ore
    - Smelt_Netherquartz_Ore
    repair_inputs:
      Lapis Lazuli:
        amount: 6
        durability: 4
        material: INK_SACK
      Netherquartz:
        amount: 2
        material: QUARTZ
      Redstone:
        amount: 5
        material: REDSTONE
    repair_multiple: 26
  Glass_Smelter:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Charcoal:
        amount: 256
        durability: 1
        material: COAL
      Sand:
        amount: 2048
        material: SAND
    name: Glass Smelter
    recipes:
    - Smelt_Glass
    - Smelt_Sandstone
    repair_inputs:
      Charcoal:
        durability: 1
        material: COAL
      Sand:
        amount: 8
        material: SAND
    repair_multiple: 26
  Gold_Axe_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Gold Ingot:
        amount: 64
        material: GOLD_INGOT
    name: Gold Axe Smithy
    recipes:
    - Gold_Axe
    repair_inputs:
      Gold Ingot:
        material: GOLD_INGOT
    repair_multiple: 7
  Gold_Boots_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Gold Ingot:
        amount: 160
        material: GOLD_INGOT
    name: Gold Boots Smithy
    recipes:
    - Gold_Boots
    repair_inputs:
      Gold Ingot:
        material: GOLD_INGOT
    repair_multiple: 16
  Gold_Chestplate_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Gold Ingot:
        amount: 320
        material: GOLD_INGOT
    name: Gold Chestplate Smithy
    recipes:
    - Gold_Chestplate
    repair_inputs:
      Gold Ingot:
        material: GOLD_INGOT
    repair_multiple: 32
  Gold_Helmet_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Gold Ingot:
        amount: 192
        material: GOLD_INGOT
    name: Gold Helmet Smithy
    recipes:
    - Gold_Helmet
    repair_inputs:
      Gold Ingot:
        material: GOLD_INGOT
    repair_multiple: 20
  Gold_Hoe_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Gold Ingot:
        amount: 32
        material: GOLD_INGOT
    name: Gold Hoe Smithy
    recipes:
    - Gold_Hoe
    repair_inputs:
      Gold Ingot:
        material: GOLD_INGOT
    repair_multiple: 4
  Gold_Leggings_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Gold Ingot:
        amount: 256
        material: GOLD_INGOT
    name: Gold Leggings Smithy
    recipes:
    - Gold_Leggings
    repair_inputs:
      Gold Ingot:
        material: GOLD_INGOT
    repair_multiple: 26
  Gold_Pickaxe_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Gold Ingot:
        amount: 96
        material: GOLD_INGOT
    name: Gold Pickaxe Smithy
    recipes:
    - Gold_Pickaxe
    repair_inputs:
      Gold Ingot:
        material: GOLD_INGOT
    repair_multiple: 10
  Gold_Spade_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Gold Ingot:
        amount: 48
        material: GOLD_INGOT
    name: Gold Spade Smithy
    recipes:
    - Gold_Spade
    repair_inputs:
      Gold Ingot:
        material: GOLD_INGOT
    repair_multiple: 5
  Gold_Sword_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Gold Ingot:
        amount: 80
        material: GOLD_INGOT
    name: Gold Sword Smithy
    recipes:
    - Gold_Sword
    repair_inputs:
      Gold Ingot:
        material: GOLD_INGOT
    repair_multiple: 8
  Gray_Wool_Processing:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Bone Meal:
        amount: 20
        durability: 15
        material: INK_SACK
      Cactus Green:
        amount: 20
        durability: 2
        material: INK_SACK
      Cocoa:
        amount: 20
        durability: 3
        material: INK_SACK
      Cyan Dye:
        amount: 20
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        amount: 20
        durability: 11
        material: INK_SACK
      Gray Dye:
        amount: 20
        durability: 8
        material: INK_SACK
      Gray Wool:
        amount: 20
        durability: 7
        material: WOOL
      Ink Sack:
        amount: 20
        material: INK_SACK
      Lapis Lazuli:
        amount: 20
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        amount: 20
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        amount: 20
        durability: 7
        material: INK_SACK
      Lime Dye:
        amount: 20
        durability: 10
        material: INK_SACK
      Magenta Dye:
        amount: 20
        durability: 13
        material: INK_SACK
      Orange Dye:
        amount: 20
        durability: 14
        material: INK_SACK
      Pink Dye:
        amount: 20
        durability: 9
        material: INK_SACK
      Purple Dye:
        amount: 20
        durability: 5
        material: INK_SACK
      Rose Red:
        amount: 20
        durability: 1
        material: INK_SACK
    name: Gray Wool Processing
    recipes:
    - Dye_Gray_Wool_Blue
    - Dye_Gray_Wool_Brown
    - Dye_Gray_Wool_Purple
    - Dye_Gray_Wool_Yellow
    - Dye_Gray_Wool_Black
    - Dye_Gray_Wool_Magenta
    - Dye_Gray_Wool_Pink
    - Dye_Gray_Wool_Cyan
    - Dye_Gray_Wool_Orange
    - Dye_Gray_Wool_Green
    - Dye_Gray_Wool_White
    - Dye_Gray_Wool_Light_Gray
    - Dye_Gray_Wool_Light_Blue
    - Dye_Gray_Wool_Red
    - Dye_Gray_Wool_Lime
    repair_inputs:
      Bone Meal:
        durability: 15
        material: INK_SACK
      Cactus Green:
        durability: 2
        material: INK_SACK
      Cocoa:
        durability: 3
        material: INK_SACK
      Cyan Dye:
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        durability: 11
        material: INK_SACK
      Gray Dye:
        durability: 8
        material: INK_SACK
      Gray Wool:
        durability: 7
        material: WOOL
      Ink Sack:
        material: INK_SACK
      Lapis Lazuli:
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        durability: 7
        material: INK_SACK
      Lime Dye:
        durability: 10
        material: INK_SACK
      Magenta Dye:
        durability: 13
        material: INK_SACK
      Orange Dye:
        durability: 14
        material: INK_SACK
      Pink Dye:
        durability: 9
        material: INK_SACK
      Purple Dye:
        durability: 5
        material: INK_SACK
      Rose Red:
        durability: 1
        material: INK_SACK
    repair_multiple: 2
  Grill:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Cooked Beef:
        amount: 64
        material: COOKED_BEEF
      Cooked Chicken:
        amount: 192
        material: COOKED_CHICKEN
      Cooked Fish:
        amount: 16
        material: COOKED_FISH
      Grilled Pork:
        amount: 160
        material: GRILLED_PORK
    name: Bakery
    recipes:
    - Cooked_Chicken
    - Cooked_Fish
    - Grilled_Pork
    - Cooked_Beef
    repair_inputs:
      Cooked Beef:
        amount: 4
        material: COOKED_BEEF
      Cooked Chicken:
        amount: 10
        material: COOKED_CHICKEN
      Cooked Fish:
        material: COOKED_FISH
      Grilled Pork:
        amount: 8
        material: GRILLED_PORK
    repair_multiple: 2
  Horse_Factory:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Golden Apple:
        amount: 64
        durability: 0
        material: GOLDEN_APPLE
      Hay Bale:
        amount: 64
        material: HAY_BLOCK
      Iron Block:
        amount: 64
        material: IRON_BLOCK
    name: Animal Husbandry Factory
    recipes:
    - Produce_Saddle
    - Produce_Diamond_Horse_Armor
    - Produce_Gold_Horse_Armor
    - Produce_Iron_Horse_Armor
    repair_inputs:
      Golden Apple:
        amount: 6
        durability: 0
        material: GOLDEN_APPLE
      Hay Bale:
        amount: 6
        material: HAY_BLOCK
      Iron Block:
        amount: 6
        material: IRON_BLOCK
    repair_multiple: 10
  Iron_Axe_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Iron Ingot:
        amount: 64
        material: IRON_INGOT
    name: Iron Axe Smithy
    recipes:
    - Iron_Axe
    repair_inputs:
      Iron Ingot:
        material: IRON_INGOT
    repair_multiple: 7
  Iron_Boots_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Iron Ingot:
        amount: 160
        material: IRON_INGOT
    name: Iron Boots Smithy
    recipes:
    - Iron_Boots
    repair_inputs:
      Iron Ingot:
        material: IRON_INGOT
    repair_multiple: 16
  Iron_Cauldron:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Iron Ingot:
        amount: 200
        material: IRON_INGOT
    name: Iron Cauldron
    recipes:
    - Iron_XP_Bottle_0
    - Iron_XP_Bottle_1
    - Iron_XP_Bottle_2
    - Iron_XP_Bottle_3
    repair_inputs:
      Iron Ingot:
        material: IRON_INGOT
    repair_multiple: 20
  Iron_Chestplate_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Iron Ingot:
        amount: 320
        material: IRON_INGOT
    name: Iron Chestplate Smithy
    recipes:
    - Iron_Chestplate
    repair_inputs:
      Iron Ingot:
        material: IRON_INGOT
    repair_multiple: 32
  Iron_Helmet_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Iron Ingot:
        amount: 192
        material: IRON_INGOT
    name: Iron Helmet Smithy
    recipes:
    - Iron_Helmet
    repair_inputs:
      Iron Ingot:
        material: IRON_INGOT
    repair_multiple: 20
  Iron_Hoe_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Iron Ingot:
        amount: 32
        material: IRON_INGOT
    name: Iron Hoe Smithy
    recipes:
    - Iron_Hoe
    repair_inputs:
      Iron Ingot:
        material: IRON_INGOT
    repair_multiple: 4
  Iron_Leggings_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Iron Ingot:
        amount: 256
        material: IRON_INGOT
    name: Iron Leggings Smithy
    recipes:
    - Iron_Leggings
    repair_inputs:
      Iron Ingot:
        material: IRON_INGOT
    repair_multiple: 26
  Iron_Pickaxe_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Iron Ingot:
        amount: 96
        material: IRON_INGOT
    name: Iron Pickaxe Smithy
    recipes:
    - Iron_Pickaxe
    repair_inputs:
      Iron Ingot:
        material: IRON_INGOT
    repair_multiple: 10
  Iron_Spade_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Iron Ingot:
        amount: 48
        material: IRON_INGOT
    name: Iron Spade Smithy
    recipes:
    - Iron_Spade
    repair_inputs:
      Iron Ingot:
        material: IRON_INGOT
    repair_multiple: 5
  Iron_Sword_Smithy:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Iron Ingot:
        amount: 80
        material: IRON_INGOT
    name: Iron Sword Smithy
    recipes:
    - Iron_Sword
    repair_inputs:
      Iron Ingot:
        material: IRON_INGOT
    repair_multiple: 8
  Light_Gray_Wool_Processing:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Bone Meal:
        amount: 20
        durability: 15
        material: INK_SACK
      Cactus Green:
        amount: 20
        durability: 2
        material: INK_SACK
      Cocoa:
        amount: 20
        durability: 3
        material: INK_SACK
      Cyan Dye:
        amount: 20
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        amount: 20
        durability: 11
        material: INK_SACK
      Gray Dye:
        amount: 20
        durability: 8
        material: INK_SACK
      Ink Sack:
        amount: 20
        material: INK_SACK
      Lapis Lazuli:
        amount: 20
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        amount: 20
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        amount: 20
        durability: 7
        material: INK_SACK
      Light Gray Wool:
        amount: 20
        durability: 8
        material: WOOL
      Lime Dye:
        amount: 20
        durability: 10
        material: INK_SACK
      Magenta Dye:
        amount: 20
        durability: 13
        material: INK_SACK
      Orange Dye:
        amount: 20
        durability: 14
        material: INK_SACK
      Pink Dye:
        amount: 20
        durability: 9
        material: INK_SACK
      Purple Dye:
        amount: 20
        durability: 5
        material: INK_SACK
      Rose Red:
        amount: 20
        durability: 1
        material: INK_SACK
    name: Light Gray Wool Processing
    recipes:
    - Dye_Light_Gray_Wool_Blue
    - Dye_Light_Gray_Wool_Gray
    - Dye_Light_Gray_Wool_Brown
    - Dye_Light_Gray_Wool_Purple
    - Dye_Light_Gray_Wool_Yellow
    - Dye_Light_Gray_Wool_Black
    - Dye_Light_Gray_Wool_Magenta
    - Dye_Light_Gray_Wool_Pink
    - Dye_Light_Gray_Wool_Cyan
    - Dye_Light_Gray_Wool_Orange
    - Dye_Light_Gray_Wool_Green
    - Dye_Light_Gray_Wool_White
    - Dye_Light_Gray_Wool_Light_Blue
    - Dye_Light_Gray_Wool_Red
    - Dye_Light_Gray_Wool_Lime
    repair_inputs:
      Bone Meal:
        durability: 15
        material: INK_SACK
      Cactus Green:
        durability: 2
        material: INK_SACK
      Cocoa:
        durability: 3
        material: INK_SACK
      Cyan Dye:
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        durability: 11
        material: INK_SACK
      Gray Dye:
        durability: 8
        material: INK_SACK
      Ink Sack:
        material: INK_SACK
      Lapis Lazuli:
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        durability: 7
        material: INK_SACK
      Light Gray Wool:
        durability: 8
        material: WOOL
      Lime Dye:
        durability: 10
        material: INK_SACK
      Magenta Dye:
        durability: 13
        material: INK_SACK
      Orange Dye:
        durability: 14
        material: INK_SACK
      Pink Dye:
        durability: 9
        material: INK_SACK
      Purple Dye:
        durability: 5
        material: INK_SACK
      Rose Red:
        durability: 1
        material: INK_SACK
    repair_multiple: 2
  Nether_Brick_Smelter:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Charcoal:
        amount: 256
        durability: 1
        material: COAL
      Netherrack:
        amount: 2048
        material: NETHERRACK
    name: Nether Brick Smelter
    recipes:
    - Smelt_Nether_bricks
    repair_inputs:
      Charcoal:
        durability: 1
        material: COAL
      Netherrack:
        amount: 8
        material: NETHERRACK
    repair_multiple: 26
  Pink_Wool_Processing:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Bone Meal:
        amount: 20
        durability: 15
        material: INK_SACK
      Cactus Green:
        amount: 20
        durability: 2
        material: INK_SACK
      Cocoa:
        amount: 20
        durability: 3
        material: INK_SACK
      Cyan Dye:
        amount: 20
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        amount: 20
        durability: 11
        material: INK_SACK
      Gray Dye:
        amount: 20
        durability: 8
        material: INK_SACK
      Ink Sack:
        amount: 20
        material: INK_SACK
      Lapis Lazuli:
        amount: 20
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        amount: 20
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        amount: 20
        durability: 7
        material: INK_SACK
      Lime Dye:
        amount: 20
        durability: 10
        material: INK_SACK
      Magenta Dye:
        amount: 20
        durability: 13
        material: INK_SACK
      Orange Dye:
        amount: 20
        durability: 14
        material: INK_SACK
      Pink Dye:
        amount: 20
        durability: 9
        material: INK_SACK
      Pink Wool:
        amount: 20
        durability: 6
        material: WOOL
      Purple Dye:
        amount: 20
        durability: 5
        material: INK_SACK
      Rose Red:
        amount: 20
        durability: 1
        material: INK_SACK
    name: Pink Wool Processing
    recipes:
    - Dye_Pink_Wool_Blue
    - Dye_Pink_Wool_Gray
    - Dye_Pink_Wool_Brown
    - Dye_Pink_Wool_Purple
    - Dye_Pink_Wool_Yellow
    - Dye_Pink_Wool_Black
    - Dye_Pink_Wool_Magenta
    - Dye_Pink_Wool_Cyan
    - Dye_Pink_Wool_Orange
    - Dye_Pink_Wool_Green
    - Dye_Pink_Wool_White
    - Dye_Pink_Wool_Light_Gray
    - Dye_Pink_Wool_Light_Blue
    - Dye_Pink_Wool_Red
    - Dye_Pink_Wool_Lime
    repair_inputs:
      Bone Meal:
        durability: 15
        material: INK_SACK
      Cactus Green:
        durability: 2
        material: INK_SACK
      Cocoa:
        durability: 3
        material: INK_SACK
      Cyan Dye:
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        durability: 11
        material: INK_SACK
      Gray Dye:
        durability: 8
        material: INK_SACK
      Ink Sack:
        material: INK_SACK
      Lapis Lazuli:
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        durability: 7
        material: INK_SACK
      Lime Dye:
        durability: 10
        material: INK_SACK
      Magenta Dye:
        durability: 13
        material: INK_SACK
      Orange Dye:
        durability: 14
        material: INK_SACK
      Pink Dye:
        durability: 9
        material: INK_SACK
      Pink Wool:
        durability: 6
        material: WOOL
      Purple Dye:
        durability: 5
        material: INK_SACK
      Rose Red:
        durability: 1
        material: INK_SACK
    repair_multiple: 2
  Rail_Factory:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Gold Ingot:
        amount: 192
        material: GOLD_INGOT
      Iron Ingot:
        amount: 256
        material: IRON_INGOT
      Redstone:
        amount: 32
        material: REDSTONE
      Stick:
        amount: 96
        material: STICK
    name: Rail Factory
    recipes:
    - Produce_Rail
    - Produce_Powered_Rail
    repair_inputs:
      Gold Ingot:
        amount: 5
        material: GOLD_INGOT
      Iron Ingot:
        amount: 7
        material: IRON_INGOT
      Redstone:
        material: REDSTONE
      Stick:
        amount: 3
        material: STICK
    repair_multiple: 4
  Smelter:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Coal:
        amount: 512
        material: COAL
      Diamond:
        amount: 96
        material: DIAMOND
      Gold Ingot:
        amount: 192
        material: GOLD_INGOT
      Iron Ingot:
        amount: 384
        material: IRON_INGOT
    name: Ore Smelter
    recipes:
    - Smelt_Coal_Ore
    - Smelt_Iron_Ore
    - Smelt_Gold_Ore
    - Smelt_Diamond_Ore
    repair_inputs:
      Coal:
        amount: 6
        material: COAL
      Diamond:
        material: DIAMOND
      Gold Ingot:
        amount: 2
        material: GOLD_INGOT
      Iron Ingot:
        amount: 4
        material: IRON_INGOT
    repair_multiple: 10
  Stone_Brick_Smelter:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Lapis Lazuli:
        amount: 256
        durability: 4
        material: INK_SACK
      Stone Brick:
        amount: 512
        material: SMOOTH_BRICK
    name: Fancy Stone Brick Smelter
    recipes:
    - Smelt_Cracked_Stone_Brick
    - Smelt_Mossy_Stone_Brick
    - Smelt_Chiseled_Stone_Brick
    repair_inputs:
      Lapis Lazuli:
        durability: 4
        material: INK_SACK
      Stone Brick:
        amount: 2
        material: SMOOTH_BRICK
    repair_multiple: 26
  Stone_Smelter:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Stone:
        amount: 2048
        material: STONE
    name: Stone Smelter
    recipes:
    - Smelt_Stone
    repair_inputs:
      Stone:
        material: STONE
    repair_multiple: 205
  White_Wool_Processing:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Bone Meal:
        amount: 20
        durability: 15
        material: INK_SACK
      Cactus Green:
        amount: 20
        durability: 2
        material: INK_SACK
      Cocoa:
        amount: 20
        durability: 3
        material: INK_SACK
      Cyan Dye:
        amount: 20
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        amount: 20
        durability: 11
        material: INK_SACK
      Gray Dye:
        amount: 20
        durability: 8
        material: INK_SACK
      Ink Sack:
        amount: 20
        material: INK_SACK
      Lapis Lazuli:
        amount: 20
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        amount: 20
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        amount: 20
        durability: 7
        material: INK_SACK
      Lime Dye:
        amount: 20
        durability: 10
        material: INK_SACK
      Magenta Dye:
        amount: 20
        durability: 13
        material: INK_SACK
      Orange Dye:
        amount: 20
        durability: 14
        material: INK_SACK
      Pink Dye:
        amount: 20
        durability: 9
        material: INK_SACK
      Purple Dye:
        amount: 20
        durability: 5
        material: INK_SACK
      Rose Red:
        amount: 20
        durability: 1
        material: INK_SACK
      White Wool:
        amount: 20
        material: WOOL
    name: White Wool Processing
    recipes:
    - Dye_White_Wool_Blue
    - Dye_White_Wool_Gray
    - Dye_White_Wool_Brown
    - Dye_White_Wool_Purple
    - Dye_White_Wool_Yellow
    - Dye_White_Wool_Black
    - Dye_White_Wool_Magenta
    - Dye_White_Wool_Pink
    - Dye_White_Wool_Cyan
    - Dye_White_Wool_Orange
    - Dye_White_Wool_Green
    - Dye_White_Wool_Light_Gray
    - Dye_White_Wool_Light_Blue
    - Dye_White_Wool_Red
    - Dye_White_Wool_Lime
    repair_inputs:
      Bone Meal:
        durability: 15
        material: INK_SACK
      Cactus Green:
        durability: 2
        material: INK_SACK
      Cocoa:
        durability: 3
        material: INK_SACK
      Cyan Dye:
        durability: 6
        material: INK_SACK
      Dandelion Yellow:
        durability: 11
        material: INK_SACK
      Gray Dye:
        durability: 8
        material: INK_SACK
      Ink Sack:
        material: INK_SACK
      Lapis Lazuli:
        durability: 4
        material: INK_SACK
      Light Blue Dye:
        durability: 12
        material: INK_SACK
      Light Gray Dye:
        durability: 7
        material: INK_SACK
      Lime Dye:
        durability: 10
        material: INK_SACK
      Magenta Dye:
        durability: 13
        material: INK_SACK
      Orange Dye:
        durability: 14
        material: INK_SACK
      Pink Dye:
        durability: 9
        material: INK_SACK
      Purple Dye:
        durability: 5
        material: INK_SACK
      Rose Red:
        durability: 1
        material: INK_SACK
      White Wool:
        material: WOOL
    repair_multiple: 2
  Wood_Cauldron:
    fuel:
      Charcoal:
        durability: 1
        material: COAL
    inputs:
      Stick:
        amount: 1024
        material: STICK
    name: Wood Cauldron
    recipes:
    - Wood_XP_Bottle_0
    - Wood_XP_Bottle_1
    - Wood_XP_Bottle_2
    - Wood_XP_Bottle_3
    - Wood_XP_Bottle_4
    - Wood_XP_Bottle_5
    repair_inputs:
      Stick:
        material: STICK
    repair_multiple: 103
production_general:
  disrepair_period: 14
  repair_period: 28
  update_cycle: 20
production_recipes:
  Baked_Potato:
    inputs:
      Potato:
        amount: 192
        material: POTATO_ITEM
    name: Bake Potato
    outputs:
      Baked Potato:
        amount: 384
        material: BAKED_POTATO
    production_time: 24
  Bread:
    inputs:
      Wheat:
        amount: 384
        material: WHEAT
    name: Bake Wheat
    outputs:
      Bread:
        amount: 256
        material: BREAD
    production_time: 24
  Cooked_Beef:
    inputs:
      Raw Beef:
        amount: 64
        material: RAW_BEEF
    name: Grill Raw Beef
    outputs:
      Cooked Beef:
        amount: 128
        material: COOKED_BEEF
    production_time: 6
  Cooked_Chicken:
    inputs:
      Raw Chicken:
        amount: 64
        material: RAW_CHICKEN
    name: Grill Raw Chicken
    outputs:
      Cooked Chicken:
        amount: 128
        material: COOKED_CHICKEN
    production_time: 6
  Cooked_Fish:
    inputs:
      Raw Fish:
        amount: 64
        material: RAW_FISH
    name: Grill Raw Fish
    outputs:
      Cooked Fish:
        amount: 128
        material: COOKED_FISH
    production_time: 6
  Cookie:
    inputs:
      Cocoa:
        amount: 128
        durability: 3
        material: INK_SACK
      Wheat:
        amount: 256
        material: WHEAT
    name: Bake Cocoa
    outputs:
      Cookie:
        amount: 2048
        material: COOKIE
    production_time: 24
  Diamond_Axe:
    inputs:
      Diamond:
        amount: 15
        material: DIAMOND
    name: Forge Diamond Axe.
    outputs:
      Diamond Axe:
        amount: 30
        material: DIAMOND_AXE
    production_time: 15
  Diamond_Boots:
    inputs:
      Diamond:
        amount: 20
        material: DIAMOND
    name: Forge Diamond Boots.
    outputs:
      Diamond Boots:
        amount: 15
        material: DIAMOND_BOOTS
    production_time: 20
  Diamond_Chestplate:
    inputs:
      Diamond:
        amount: 40
        material: DIAMOND
    name: Forge Diamond Chestplate.
    outputs:
      Diamond Chestplate:
        amount: 15
        material: DIAMOND_CHESTPLATE
    production_time: 40
  Diamond_Helmet:
    inputs:
      Diamond:
        amount: 25
        material: DIAMOND
    name: Forge Diamond Helmet.
    outputs:
      Diamond Helmet:
        amount: 15
        material: DIAMOND_HELMET
    production_time: 25
  Diamond_Hoe:
    inputs:
      Diamond:
        amount: 10
        material: DIAMOND
    name: Forge Diamond Hoe.
    outputs:
      Diamond Hoe:
        amount: 30
        material: DIAMOND_HOE
    production_time: 10
  Diamond_Leggings:
    inputs:
      Diamond:
        amount: 35
        material: DIAMOND
    name: Forge Diamond Leggings.
    outputs:
      Diamond Leggings:
        amount: 15
        material: DIAMOND_LEGGINGS
    production_time: 35
  Diamond_Pickaxe:
    inputs:
      Diamond:
        amount: 15
        material: DIAMOND
    name: Forge Diamond Pickaxe.
    outputs:
      Diamond Pickaxe:
        amount: 15
        material: DIAMOND_PICKAXE
    production_time: 15
  Diamond_Spade:
    inputs:
      Diamond:
        amount: 5
        material: DIAMOND
    name: Forge Diamond Spade.
    outputs:
      Diamond Spade:
        amount: 15
        material: DIAMOND_SPADE
    production_time: 5
  Diamond_Sword:
    inputs:
      Diamond:
        amount: 10
        material: DIAMOND
    name: Forge Diamond Sword.
    outputs:
      Diamond Sword:
        amount: 15
        material: DIAMOND_SWORD
    production_time: 10
  Diamond_XP_Bottle_0:
    inputs:
      Bread:
        amount: 128
        material: BREAD
      Cactus:
        amount: 256
        material: CACTUS
      Carrot:
        amount: 96
        material: CARROT_ITEM
      Glass Bottle:
        amount: 128
        material: GLASS_BOTTLE
      Grilled Pork:
        amount: 32
        material: GRILLED_PORK
      Melon Block:
        amount: 32
        material: MELON_BLOCK
      Red Mushroom:
        amount: 32
        material: RED_MUSHROOM
      Red Rose:
        amount: 8
        material: RED_ROSE
      Rotten Flesh:
        amount: 128
        material: ROTTEN_FLESH
      Vine:
        amount: 32
        material: VINE
    name: Brew XP Bottles  - 1
    outputs:
      Exp Bottle:
        amount: 128
        material: EXP_BOTTLE
  Diamond_XP_Bottle_1:
    inputs:
      Baked Potato:
        amount: 256
        material: BAKED_POTATO
      Brown Mushroom:
        amount: 64
        material: BROWN_MUSHROOM
      Cooked Chicken:
        amount: 16
        material: COOKED_CHICKEN
      Glass Bottle:
        amount: 128
        material: GLASS_BOTTLE
      Melon Block:
        amount: 32
        material: MELON_BLOCK
      Nether Wart:
        amount: 64
        material: NETHER_STALK
      Rotten Flesh:
        amount: 128
        material: ROTTEN_FLESH
      Sugar Cane:
        amount: 128
        material: SUGAR_CANE
      Vine:
        amount: 32
        material: VINE
      Yellow Flower:
        amount: 16
        material: YELLOW_FLOWER
    name: Brew XP Bottles  - 2
    outputs:
      Exp Bottle:
        amount: 128
        material: EXP_BOTTLE
  Diamond_XP_Bottle_2:
    inputs:
      Cactus:
        amount: 256
        material: CACTUS
      Cocoa:
        amount: 16
        durability: 3
        material: INK_SACK
      Cooked Fish:
        amount: 16
        material: COOKED_FISH
      Glass Bottle:
        amount: 128
        material: GLASS_BOTTLE
      Grass:
        amount: 32
        durability: 1
        material: LONG_GRASS
      Pumpkin:
        amount: 128
        material: PUMPKIN
      Red Mushroom:
        amount: 16
        material: RED_MUSHROOM
      Red Rose:
        amount: 8
        material: RED_ROSE
      Spider Eye:
        amount: 32
        material: SPIDER_EYE
      Wheat:
        amount: 128
        material: WHEAT
    name: Brew XP Bottles  - 3
    outputs:
      Exp Bottle:
        amount: 128
        material: EXP_BOTTLE
  Diamond_XP_Bottle_3:
    inputs:
      Brown Mushroom:
        amount: 64
        material: BROWN_MUSHROOM
      Cooked Beef:
        amount: 32
        material: COOKED_BEEF
      Cookie:
        amount: 256
        material: COOKIE
      Glass Bottle:
        amount: 128
        material: GLASS_BOTTLE
      Grass:
        amount: 64
        durability: 1
        material: LONG_GRASS
      Nether Wart:
        amount: 64
        material: NETHER_STALK
      Pumpkin:
        amount: 128
        material: PUMPKIN
      Spider Eye:
        amount: 32
        material: SPIDER_EYE
      Sugar Cane:
        amount: 128
        material: SUGAR_CANE
      Yellow Flower:
        amount: 16
        material: YELLOW_FLOWER
    name: Brew XP Bottles  - 4
    outputs:
      Exp Bottle:
        amount: 128
        material: EXP_BOTTLE
  Dye_Black_Wool_Blue:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Lapis Lazuli:
        amount: 4
        durability: 4
        material: INK_SACK
    name: Dye Black Wool Blue
    outputs:
      Blue Wool:
        amount: 64
        durability: 11
        material: WOOL
  Dye_Black_Wool_Brown:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Cocoa:
        amount: 4
        durability: 3
        material: INK_SACK
    name: Dye Black Wool Brown
    outputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
  Dye_Black_Wool_Cyan:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Cyan Dye:
        amount: 4
        durability: 6
        material: INK_SACK
    name: Dye Black Wool Cyan
    outputs:
      Cyan Wool:
        amount: 64
        durability: 9
        material: WOOL
  Dye_Black_Wool_Gray:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Gray Dye:
        amount: 4
        durability: 8
        material: INK_SACK
    name: Dye Black Wool Gray
    outputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
  Dye_Black_Wool_Green:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Cactus Green:
        amount: 4
        durability: 2
        material: INK_SACK
    name: Dye Black Wool Green
    outputs:
      Green Wool:
        amount: 64
        durability: 13
        material: WOOL
  Dye_Black_Wool_Light_Blue:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Light Blue Dye:
        amount: 4
        durability: 12
        material: INK_SACK
    name: Dye Black Wool Light Blue
    outputs:
      Light Blue Wool:
        amount: 64
        durability: 3
        material: WOOL
  Dye_Black_Wool_Light_Gray:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Light Gray Dye:
        amount: 4
        durability: 7
        material: INK_SACK
    name: Dye Black Wool Light Gray
    outputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
  Dye_Black_Wool_Lime:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Lime Dye:
        amount: 4
        durability: 10
        material: INK_SACK
    name: Dye Black Wool Lime
    outputs:
      Lime Wool:
        amount: 64
        durability: 5
        material: WOOL
  Dye_Black_Wool_Magenta:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Magenta Dye:
        amount: 4
        durability: 13
        material: INK_SACK
    name: Dye Black Wool Magenta
    outputs:
      Magenta Wool:
        amount: 64
        durability: 2
        material: WOOL
  Dye_Black_Wool_Orange:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Orange Dye:
        amount: 4
        durability: 14
        material: INK_SACK
    name: Dye Black Wool Orange
    outputs:
      Orange Wool:
        amount: 64
        durability: 1
        material: WOOL
  Dye_Black_Wool_Pink:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Pink Dye:
        amount: 4
        durability: 9
        material: INK_SACK
    name: Dye Black Wool Pink
    outputs:
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
  Dye_Black_Wool_Purple:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Purple Dye:
        amount: 4
        durability: 5
        material: INK_SACK
    name: Dye Black Wool Purple
    outputs:
      Purple Wool:
        amount: 64
        durability: 10
        material: WOOL
  Dye_Black_Wool_Red:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Rose Red:
        amount: 4
        durability: 1
        material: INK_SACK
    name: Dye Black Wool Red
    outputs:
      Red Wool:
        amount: 64
        durability: 14
        material: WOOL
  Dye_Black_Wool_White:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Bone Meal:
        amount: 4
        durability: 15
        material: INK_SACK
    name: Dye Black Wool White
    outputs:
      White Wool:
        amount: 64
        material: WOOL
  Dye_Black_Wool_Yellow:
    inputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
      Dandelion Yellow:
        amount: 4
        durability: 11
        material: INK_SACK
    name: Dye Black Wool Yellow
    outputs:
      Yellow Wool:
        amount: 64
        durability: 4
        material: WOOL
  Dye_Brown_Wool_Black:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Ink Sack:
        amount: 4
        material: INK_SACK
    name: Dye Brown Wool Black
    outputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
  Dye_Brown_Wool_Blue:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Lapis Lazuli:
        amount: 4
        durability: 4
        material: INK_SACK
    name: Dye Brown Wool Blue
    outputs:
      Blue Wool:
        amount: 64
        durability: 11
        material: WOOL
  Dye_Brown_Wool_Cyan:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Cyan Dye:
        amount: 4
        durability: 6
        material: INK_SACK
    name: Dye Brown Wool Cyan
    outputs:
      Cyan Wool:
        amount: 64
        durability: 9
        material: WOOL
  Dye_Brown_Wool_Gray:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Gray Dye:
        amount: 4
        durability: 8
        material: INK_SACK
    name: Dye Brown Wool Gray
    outputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
  Dye_Brown_Wool_Green:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Cactus Green:
        amount: 4
        durability: 2
        material: INK_SACK
    name: Dye Brown Wool Green
    outputs:
      Green Wool:
        amount: 64
        durability: 13
        material: WOOL
  Dye_Brown_Wool_Light_Blue:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Light Blue Dye:
        amount: 4
        durability: 12
        material: INK_SACK
    name: Dye Brown Wool Light Blue
    outputs:
      Light Blue Wool:
        amount: 64
        durability: 3
        material: WOOL
  Dye_Brown_Wool_Light_Gray:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Light Gray Dye:
        amount: 4
        durability: 7
        material: INK_SACK
    name: Dye Brown Wool Light Gray
    outputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
  Dye_Brown_Wool_Lime:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Lime Dye:
        amount: 4
        durability: 10
        material: INK_SACK
    name: Dye Brown Wool Lime
    outputs:
      Lime Wool:
        amount: 64
        durability: 5
        material: WOOL
  Dye_Brown_Wool_Magenta:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Magenta Dye:
        amount: 4
        durability: 13
        material: INK_SACK
    name: Dye Brown Wool Magenta
    outputs:
      Magenta Wool:
        amount: 64
        durability: 2
        material: WOOL
  Dye_Brown_Wool_Orange:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Orange Dye:
        amount: 4
        durability: 14
        material: INK_SACK
    name: Dye Brown Wool Orange
    outputs:
      Orange Wool:
        amount: 64
        durability: 1
        material: WOOL
  Dye_Brown_Wool_Pink:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Pink Dye:
        amount: 4
        durability: 9
        material: INK_SACK
    name: Dye Brown Wool Pink
    outputs:
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
  Dye_Brown_Wool_Purple:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Purple Dye:
        amount: 4
        durability: 5
        material: INK_SACK
    name: Dye Brown Wool Purple
    outputs:
      Purple Wool:
        amount: 64
        durability: 10
        material: WOOL
  Dye_Brown_Wool_Red:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Rose Red:
        amount: 4
        durability: 1
        material: INK_SACK
    name: Dye Brown Wool Red
    outputs:
      Red Wool:
        amount: 64
        durability: 14
        material: WOOL
  Dye_Brown_Wool_White:
    inputs:
      Bone Meal:
        amount: 4
        durability: 15
        material: INK_SACK
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
    name: Dye Brown Wool White
    outputs:
      White Wool:
        amount: 64
        material: WOOL
  Dye_Brown_Wool_Yellow:
    inputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
      Dandelion Yellow:
        amount: 4
        durability: 11
        material: INK_SACK
    name: Dye Brown Wool Yellow
    outputs:
      Yellow Wool:
        amount: 64
        durability: 4
        material: WOOL
  Dye_Gray_Wool_Black:
    inputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
      Ink Sack:
        amount: 4
        material: INK_SACK
    name: Dye Gray Wool Black
    outputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
  Dye_Gray_Wool_Blue:
    inputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
      Lapis Lazuli:
        amount: 4
        durability: 4
        material: INK_SACK
    name: Dye Gray Wool Blue
    outputs:
      Blue Wool:
        amount: 64
        durability: 11
        material: WOOL
  Dye_Gray_Wool_Brown:
    inputs:
      Cocoa:
        amount: 4
        durability: 3
        material: INK_SACK
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
    name: Dye Gray Wool Brown
    outputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
  Dye_Gray_Wool_Cyan:
    inputs:
      Cyan Dye:
        amount: 4
        durability: 6
        material: INK_SACK
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
    name: Dye Gray Wool Cyan
    outputs:
      Cyan Wool:
        amount: 64
        durability: 9
        material: WOOL
  Dye_Gray_Wool_Green:
    inputs:
      Cactus Green:
        amount: 4
        durability: 2
        material: INK_SACK
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
    name: Dye Gray Wool Green
    outputs:
      Green Wool:
        amount: 64
        durability: 13
        material: WOOL
  Dye_Gray_Wool_Light_Blue:
    inputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
      Light Blue Dye:
        amount: 4
        durability: 12
        material: INK_SACK
    name: Dye Gray Wool Light Blue
    outputs:
      Light Blue Wool:
        amount: 64
        durability: 3
        material: WOOL
  Dye_Gray_Wool_Light_Gray:
    inputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
      Light Gray Dye:
        amount: 4
        durability: 7
        material: INK_SACK
    name: Dye Gray Wool Light Gray
    outputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
  Dye_Gray_Wool_Lime:
    inputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
      Lime Dye:
        amount: 4
        durability: 10
        material: INK_SACK
    name: Dye Gray Wool Lime
    outputs:
      Lime Wool:
        amount: 64
        durability: 5
        material: WOOL
  Dye_Gray_Wool_Magenta:
    inputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
      Magenta Dye:
        amount: 4
        durability: 13
        material: INK_SACK
    name: Dye Gray Wool Magenta
    outputs:
      Magenta Wool:
        amount: 64
        durability: 2
        material: WOOL
  Dye_Gray_Wool_Orange:
    inputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
      Orange Dye:
        amount: 4
        durability: 14
        material: INK_SACK
    name: Dye Gray Wool Orange
    outputs:
      Orange Wool:
        amount: 64
        durability: 1
        material: WOOL
  Dye_Gray_Wool_Pink:
    inputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
      Pink Dye:
        amount: 4
        durability: 9
        material: INK_SACK
    name: Dye Gray Wool Pink
    outputs:
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
  Dye_Gray_Wool_Purple:
    inputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
      Purple Dye:
        amount: 4
        durability: 5
        material: INK_SACK
    name: Dye Gray Wool Purple
    outputs:
      Purple Wool:
        amount: 64
        durability: 10
        material: WOOL
  Dye_Gray_Wool_Red:
    inputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
      Rose Red:
        amount: 4
        durability: 1
        material: INK_SACK
    name: Dye Gray Wool Red
    outputs:
      Red Wool:
        amount: 64
        durability: 14
        material: WOOL
  Dye_Gray_Wool_White:
    inputs:
      Bone Meal:
        amount: 4
        durability: 15
        material: INK_SACK
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
    name: Dye Gray Wool White
    outputs:
      White Wool:
        amount: 64
        material: WOOL
  Dye_Gray_Wool_Yellow:
    inputs:
      Dandelion Yellow:
        amount: 4
        durability: 11
        material: INK_SACK
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
    name: Dye Gray Wool Yellow
    outputs:
      Yellow Wool:
        amount: 64
        durability: 4
        material: WOOL
  Dye_Light_Gray_Wool_Black:
    inputs:
      Ink Sack:
        amount: 4
        material: INK_SACK
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
    name: Dye Light Gray Wool Black
    outputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
  Dye_Light_Gray_Wool_Blue:
    inputs:
      Lapis Lazuli:
        amount: 4
        durability: 4
        material: INK_SACK
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
    name: Dye Light Gray Wool Blue
    outputs:
      Blue Wool:
        amount: 64
        durability: 11
        material: WOOL
  Dye_Light_Gray_Wool_Brown:
    inputs:
      Cocoa:
        amount: 4
        durability: 3
        material: INK_SACK
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
    name: Dye Light Gray Wool Brown
    outputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
  Dye_Light_Gray_Wool_Cyan:
    inputs:
      Cyan Dye:
        amount: 4
        durability: 6
        material: INK_SACK
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
    name: Dye Light Gray Wool Cyan
    outputs:
      Cyan Wool:
        amount: 64
        durability: 9
        material: WOOL
  Dye_Light_Gray_Wool_Gray:
    inputs:
      Gray Dye:
        amount: 4
        durability: 8
        material: INK_SACK
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
    name: Dye Light Gray Wool Gray
    outputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
  Dye_Light_Gray_Wool_Green:
    inputs:
      Cactus Green:
        amount: 4
        durability: 2
        material: INK_SACK
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
    name: Dye Light Gray Wool Green
    outputs:
      Green Wool:
        amount: 64
        durability: 13
        material: WOOL
  Dye_Light_Gray_Wool_Light_Blue:
    inputs:
      Light Blue Dye:
        amount: 4
        durability: 12
        material: INK_SACK
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
    name: Dye Light Gray Wool Light Blue
    outputs:
      Light Blue Wool:
        amount: 64
        durability: 3
        material: WOOL
  Dye_Light_Gray_Wool_Lime:
    inputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
      Lime Dye:
        amount: 4
        durability: 10
        material: INK_SACK
    name: Dye Light Gray Wool Lime
    outputs:
      Lime Wool:
        amount: 64
        durability: 5
        material: WOOL
  Dye_Light_Gray_Wool_Magenta:
    inputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
      Magenta Dye:
        amount: 4
        durability: 13
        material: INK_SACK
    name: Dye Light Gray Wool Magenta
    outputs:
      Magenta Wool:
        amount: 64
        durability: 2
        material: WOOL
  Dye_Light_Gray_Wool_Orange:
    inputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
      Orange Dye:
        amount: 4
        durability: 14
        material: INK_SACK
    name: Dye Light Gray Wool Orange
    outputs:
      Orange Wool:
        amount: 64
        durability: 1
        material: WOOL
  Dye_Light_Gray_Wool_Pink:
    inputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
      Pink Dye:
        amount: 4
        durability: 9
        material: INK_SACK
    name: Dye Light Gray Wool Pink
    outputs:
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
  Dye_Light_Gray_Wool_Purple:
    inputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
      Purple Dye:
        amount: 4
        durability: 5
        material: INK_SACK
    name: Dye Light Gray Wool Purple
    outputs:
      Purple Wool:
        amount: 64
        durability: 10
        material: WOOL
  Dye_Light_Gray_Wool_Red:
    inputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
      Rose Red:
        amount: 4
        durability: 1
        material: INK_SACK
    name: Dye Light Gray Wool Red
    outputs:
      Red Wool:
        amount: 64
        durability: 14
        material: WOOL
  Dye_Light_Gray_Wool_White:
    inputs:
      Bone Meal:
        amount: 4
        durability: 15
        material: INK_SACK
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
    name: Dye Light Gray Wool White
    outputs:
      White Wool:
        amount: 64
        material: WOOL
  Dye_Light_Gray_Wool_Yellow:
    inputs:
      Dandelion Yellow:
        amount: 4
        durability: 11
        material: INK_SACK
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
    name: Dye Light Gray Wool Yellow
    outputs:
      Yellow Wool:
        amount: 64
        durability: 4
        material: WOOL
  Dye_Pink_Wool_Black:
    inputs:
      Ink Sack:
        amount: 4
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Black
    outputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
  Dye_Pink_Wool_Blue:
    inputs:
      Lapis Lazuli:
        amount: 4
        durability: 4
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Blue
    outputs:
      Blue Wool:
        amount: 64
        durability: 11
        material: WOOL
  Dye_Pink_Wool_Brown:
    inputs:
      Cocoa:
        amount: 4
        durability: 3
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Brown
    outputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
  Dye_Pink_Wool_Cyan:
    inputs:
      Cyan Dye:
        amount: 4
        durability: 6
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Cyan
    outputs:
      Cyan Wool:
        amount: 64
        durability: 9
        material: WOOL
  Dye_Pink_Wool_Gray:
    inputs:
      Gray Dye:
        amount: 4
        durability: 8
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Gray
    outputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
  Dye_Pink_Wool_Green:
    inputs:
      Cactus Green:
        amount: 4
        durability: 2
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Green
    outputs:
      Green Wool:
        amount: 64
        durability: 13
        material: WOOL
  Dye_Pink_Wool_Light_Blue:
    inputs:
      Light Blue Dye:
        amount: 4
        durability: 12
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Light Blue
    outputs:
      Light Blue Wool:
        amount: 64
        durability: 3
        material: WOOL
  Dye_Pink_Wool_Light_Gray:
    inputs:
      Light Gray Dye:
        amount: 4
        durability: 7
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Light Gray
    outputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
  Dye_Pink_Wool_Lime:
    inputs:
      Lime Dye:
        amount: 4
        durability: 10
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Lime
    outputs:
      Lime Wool:
        amount: 64
        durability: 5
        material: WOOL
  Dye_Pink_Wool_Magenta:
    inputs:
      Magenta Dye:
        amount: 4
        durability: 13
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Magenta
    outputs:
      Magenta Wool:
        amount: 64
        durability: 2
        material: WOOL
  Dye_Pink_Wool_Orange:
    inputs:
      Orange Dye:
        amount: 4
        durability: 14
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Orange
    outputs:
      Orange Wool:
        amount: 64
        durability: 1
        material: WOOL
  Dye_Pink_Wool_Purple:
    inputs:
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
      Purple Dye:
        amount: 4
        durability: 5
        material: INK_SACK
    name: Dye Pink Wool Purple
    outputs:
      Purple Wool:
        amount: 64
        durability: 10
        material: WOOL
  Dye_Pink_Wool_Red:
    inputs:
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
      Rose Red:
        amount: 4
        durability: 1
        material: INK_SACK
    name: Dye Pink Wool Red
    outputs:
      Red Wool:
        amount: 64
        durability: 14
        material: WOOL
  Dye_Pink_Wool_White:
    inputs:
      Bone Meal:
        amount: 4
        durability: 15
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool White
    outputs:
      White Wool:
        amount: 64
        material: WOOL
  Dye_Pink_Wool_Yellow:
    inputs:
      Dandelion Yellow:
        amount: 4
        durability: 11
        material: INK_SACK
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
    name: Dye Pink Wool Yellow
    outputs:
      Yellow Wool:
        amount: 64
        durability: 4
        material: WOOL
  Dye_White_Wool_Black:
    inputs:
      Ink Sack:
        amount: 4
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Black
    outputs:
      Black Wool:
        amount: 64
        durability: 15
        material: WOOL
  Dye_White_Wool_Blue:
    inputs:
      Lapis Lazuli:
        amount: 4
        durability: 4
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Blue
    outputs:
      Blue Wool:
        amount: 64
        durability: 11
        material: WOOL
  Dye_White_Wool_Brown:
    inputs:
      Cocoa:
        amount: 4
        durability: 3
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Brown
    outputs:
      Brown Wool:
        amount: 64
        durability: 12
        material: WOOL
  Dye_White_Wool_Cyan:
    inputs:
      Cyan Dye:
        amount: 4
        durability: 6
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Cyan
    outputs:
      Cyan Wool:
        amount: 64
        durability: 9
        material: WOOL
  Dye_White_Wool_Gray:
    inputs:
      Gray Dye:
        amount: 4
        durability: 8
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Gray
    outputs:
      Gray Wool:
        amount: 64
        durability: 7
        material: WOOL
  Dye_White_Wool_Green:
    inputs:
      Cactus Green:
        amount: 4
        durability: 2
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Green
    outputs:
      Green Wool:
        amount: 64
        durability: 13
        material: WOOL
  Dye_White_Wool_Light_Blue:
    inputs:
      Light Blue Dye:
        amount: 4
        durability: 12
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Light Blue
    outputs:
      Light Blue Wool:
        amount: 64
        durability: 3
        material: WOOL
  Dye_White_Wool_Light_Gray:
    inputs:
      Light Gray Dye:
        amount: 4
        durability: 7
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Light Gray
    outputs:
      Light Gray Wool:
        amount: 64
        durability: 8
        material: WOOL
  Dye_White_Wool_Lime:
    inputs:
      Lime Dye:
        amount: 4
        durability: 10
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Lime
    outputs:
      Lime Wool:
        amount: 64
        durability: 5
        material: WOOL
  Dye_White_Wool_Magenta:
    inputs:
      Magenta Dye:
        amount: 4
        durability: 13
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Magenta
    outputs:
      Magenta Wool:
        amount: 64
        durability: 2
        material: WOOL
  Dye_White_Wool_Orange:
    inputs:
      Orange Dye:
        amount: 4
        durability: 14
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Orange
    outputs:
      Orange Wool:
        amount: 64
        durability: 1
        material: WOOL
  Dye_White_Wool_Pink:
    inputs:
      Pink Dye:
        amount: 4
        durability: 9
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Pink
    outputs:
      Pink Wool:
        amount: 64
        durability: 6
        material: WOOL
  Dye_White_Wool_Purple:
    inputs:
      Purple Dye:
        amount: 4
        durability: 5
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Purple
    outputs:
      Purple Wool:
        amount: 64
        durability: 10
        material: WOOL
  Dye_White_Wool_Red:
    inputs:
      Rose Red:
        amount: 4
        durability: 1
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Red
    outputs:
      Red Wool:
        amount: 64
        durability: 14
        material: WOOL
  Dye_White_Wool_Yellow:
    inputs:
      Dandelion Yellow:
        amount: 4
        durability: 11
        material: INK_SACK
      White Wool:
        amount: 64
        material: WOOL
    name: Dye White Wool Yellow
    outputs:
      Yellow Wool:
        amount: 64
        durability: 4
        material: WOOL
  Gold_Axe:
    enchantments:
      Bane of the Anthropods 1:
        level: 1
        probability: 0.4
        type: DAMAGE_ARTHROPODS
      Bane of the Anthropods 2:
        level: 2
        probability: 0.3
        type: DAMAGE_ARTHROPODS
      Bane of the Anthropods 3:
        level: 3
        probability: 0.2
        type: DAMAGE_ARTHROPODS
      Bane of the Anthropods 4:
        level: 4
        probability: 0.1
        type: DAMAGE_ARTHROPODS
      Bane of the Anthropods 5:
        level: 5
        probability: 0.3
        type: DAMAGE_ARTHROPODS
      Efficiency 1:
        level: 1
        probability: 0.3
        type: DIG_SPEED
      Efficiency 2:
        level: 2
        probability: 0.2
        type: DIG_SPEED
      Efficiency 3:
        level: 3
        probability: 0.1
        type: DIG_SPEED
      Efficiency 4:
        level: 4
        probability: 0.05
        type: DIG_SPEED
      Efficiency 5:
        level: 5
        probability: 0.01
        type: DIG_SPEED
      Looting 1:
        level: 1
        probability: 0.5
        type: LOOT_BONUS_MOBS
      Looting 2:
        level: 2
        probability: 0.4
        type: LOOT_BONUS_MOBS
      Looting 3:
        level: 3
        probability: 0.3
        type: LOOT_BONUS_MOBS
      Silk Touch 1:
        level: 1
        probability: 0.1
        type: SILK_TOUCH
      Smite 1:
        level: 1
        probability: 0.4
        type: DAMAGE_UNDEAD
      Smite 2:
        level: 2
        probability: 0.3
        type: DAMAGE_UNDEAD
      Smite 3:
        level: 3
        probability: 0.2
        type: DAMAGE_UNDEAD
      Smite 4:
        level: 4
        probability: 0.1
        type: DAMAGE_UNDEAD
      Smite 5:
        level: 5
        probability: 0.05
        type: DAMAGE_UNDEAD
      Unbreaking 3:
        level: 3
        type: DURABILITY
    inputs:
      Gold Ingot:
        amount: 15
        material: GOLD_INGOT
    name: Forge Gold Axe.
    outputs:
      Gold Axe:
        amount: 30
        material: GOLD_AXE
    production_time: 15
  Gold_Boots:
    enchantments:
      Blast Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_EXPLOSIONS
      Blast Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_EXPLOSIONS
      Blast Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_EXPLOSIONS
      Blast Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_EXPLOSIONS
      Feather Falling 1:
        level: 1
        probability: 0.5
        type: PROTECTION_FALL
      Feather Falling 2:
        level: 2
        probability: 0.4
        type: PROTECTION_FALL
      Feather Falling 3:
        level: 3
        probability: 0.3
        type: PROTECTION_FALL
      Feather Falling 4:
        level: 4
        probability: 0.4
        type: PROTECTION_FALL
      Fire Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_FIRE
      Fire Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_FIRE
      Fire Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_FIRE
      Fire Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_FIRE
      Projectile Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_PROJECTILE
      Projectile Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_PROJECTILE
      Projectile Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_PROJECTILE
      Projectile Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_PROJECTILE
      Unbreaking 3:
        level: 3
        type: DURABILITY
    inputs:
      Gold Ingot:
        amount: 20
        material: GOLD_INGOT
    name: Forge Gold Boots.
    outputs:
      Gold Boots:
        amount: 15
        durability: -218
        material: GOLD_BOOTS
    production_time: 20
  Gold_Chestplate:
    enchantments:
      Blast Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_EXPLOSIONS
      Blast Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_EXPLOSIONS
      Blast Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_EXPLOSIONS
      Blast Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_EXPLOSIONS
      Fire Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_FIRE
      Fire Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_FIRE
      Fire Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_FIRE
      Fire Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_FIRE
      Projectile Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_PROJECTILE
      Projectile Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_PROJECTILE
      Projectile Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_PROJECTILE
      Projectile Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_PROJECTILE
      Unbreaking 3:
        level: 3
        type: DURABILITY
    inputs:
      Gold Ingot:
        amount: 40
        material: GOLD_INGOT
    name: Forge Gold Chestplate.
    outputs:
      Gold Chestplate:
        amount: 15
        durability: -218
        material: GOLD_CHESTPLATE
    production_time: 40
  Gold_Helmet:
    enchantments:
      Blast Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_EXPLOSIONS
      Blast Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_EXPLOSIONS
      Blast Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_EXPLOSIONS
      Blast Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_EXPLOSIONS
      Fire Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_FIRE
      Fire Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_FIRE
      Fire Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_FIRE
      Fire Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_FIRE
      Projectile Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_PROJECTILE
      Projectile Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_PROJECTILE
      Projectile Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_PROJECTILE
      Projectile Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_PROJECTILE
      Respiration 1:
        level: 1
        probability: 0.5
        type: OXYGEN
      Respiration 2:
        level: 2
        probability: 0.4
        type: OXYGEN
      Respiration 3:
        level: 3
        probability: 0.3
        type: OXYGEN
      Respiration 4:
        level: 4
        probability: 0.4
        type: OXYGEN
      Unbreaking 3:
        level: 3
        type: DURABILITY
    inputs:
      Gold Ingot:
        amount: 25
        material: GOLD_INGOT
    name: Forge Gold Helmet.
    outputs:
      Gold Helmet:
        amount: 15
        durability: -218
        material: GOLD_HELMET
    production_time: 25
  Gold_Hoe:
    enchantments:
      Unbreaking 3:
        level: 3
        type: DURABILITY
    inputs:
      Gold Ingot:
        amount: 10
        material: GOLD_INGOT
    name: Forge Gold Hoe.
    outputs:
      Gold Hoe:
        amount: 30
        material: GOLD_HOE
    production_time: 10
  Gold_Leggings:
    enchantments:
      Blast Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_EXPLOSIONS
      Blast Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_EXPLOSIONS
      Blast Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_EXPLOSIONS
      Blast Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_EXPLOSIONS
      Fire Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_FIRE
      Fire Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_FIRE
      Fire Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_FIRE
      Fire Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_FIRE
      Projectile Protection 1:
        level: 1
        probability: 0.5
        type: PROTECTION_PROJECTILE
      Projectile Protection 2:
        level: 2
        probability: 0.4
        type: PROTECTION_PROJECTILE
      Projectile Protection 3:
        level: 3
        probability: 0.3
        type: PROTECTION_PROJECTILE
      Projectile Protection 4:
        level: 4
        probability: 0.4
        type: PROTECTION_PROJECTILE
      Unbreaking 3:
        level: 3
        type: DURABILITY
    inputs:
      Gold Ingot:
        amount: 35
        material: GOLD_INGOT
    name: Forge Gold Leggings.
    outputs:
      Gold Leggings:
        amount: 15
        durability: -218
        material: GOLD_LEGGINGS
    production_time: 35
  Gold_Pickaxe:
    enchantments:
      Efficiency 1:
        level: 1
        probability: 0.3
        type: DIG_SPEED
      Efficiency 2:
        level: 2
        probability: 0.2
        type: DIG_SPEED
      Efficiency 3:
        level: 3
        probability: 0.1
        type: DIG_SPEED
      Efficiency 4:
        level: 4
        probability: 0.05
        type: DIG_SPEED
      Efficiency 5:
        level: 5
        probability: 0.01
        type: DIG_SPEED
      Silk Touch 1:
        level: 1
        probability: 0.1
        type: SILK_TOUCH
      Unbreaking 3:
        level: 3
        type: DURABILITY
    inputs:
      Gold Ingot:
        amount: 15
        material: GOLD_INGOT
    name: Forge Gold Pickaxe.
    outputs:
      Gold Pickaxe:
        amount: 15
        material: GOLD_PICKAXE
    production_time: 15
  Gold_Spade:
    inputs:
      Gold Ingot:
        amount: 5
        material: GOLD_INGOT
    name: Forge Gold Spade.
    outputs:
      Gold Spade:
        amount: 15
        material: GOLD_SPADE
    production_time: 5
  Gold_Sword:
    enchantments:
      Bane of the Anthropods 1:
        level: 1
        probability: 0.4
        type: DAMAGE_ARTHROPODS
      Bane of the Anthropods 2:
        level: 2
        probability: 0.3
        type: DAMAGE_ARTHROPODS
      Bane of the Anthropods 3:
        level: 3
        probability: 0.2
        type: DAMAGE_ARTHROPODS
      Bane of the Anthropods 4:
        level: 4
        probability: 0.1
        type: DAMAGE_ARTHROPODS
      Bane of the Anthropods 5:
        level: 5
        probability: 0.3
        type: DAMAGE_ARTHROPODS
      Looting 1:
        level: 1
        probability: 0.5
        type: LOOT_BONUS_MOBS
      Looting 2:
        level: 2
        probability: 0.4
        type: LOOT_BONUS_MOBS
      Looting 3:
        level: 3
        probability: 0.3
        type: LOOT_BONUS_MOBS
      Smite 1:
        level: 1
        probability: 0.4
        type: DAMAGE_UNDEAD
      Smite 2:
        level: 2
        probability: 0.3
        type: DAMAGE_UNDEAD
      Smite 3:
        level: 3
        probability: 0.2
        type: DAMAGE_UNDEAD
      Smite 4:
        level: 4
        probability: 0.1
        type: DAMAGE_UNDEAD
      Smite 5:
        level: 5
        probability: 0.05
        type: DAMAGE_UNDEAD
      Unbreaking 3:
        level: 3
        type: DURABILITY
    inputs:
      Gold Ingot:
        amount: 10
        material: GOLD_INGOT
    name: Forge Gold Sword.
    outputs:
      Gold Sword:
        amount: 15
        material: GOLD_SWORD
    production_time: 10
  Grilled_Pork:
    inputs:
      Pork:
        amount: 64
        material: PORK
    name: Grill Pork
    outputs:
      Grilled Pork:
        amount: 128
        material: GRILLED_PORK
    production_time: 6
  Iron_Axe:
    inputs:
      Iron Ingot:
        amount: 15
        material: IRON_INGOT
    name: Forge Iron Axe.
    outputs:
      Iron Axe:
        amount: 30
        material: IRON_AXE
    production_time: 15
  Iron_Boots:
    inputs:
      Iron Ingot:
        amount: 20
        material: IRON_INGOT
    name: Forge Iron Boots.
    outputs:
      Iron Boots:
        amount: 15
        material: IRON_BOOTS
    production_time: 20
  Iron_Chestplate:
    inputs:
      Iron Ingot:
        amount: 40
        material: IRON_INGOT
    name: Forge Iron Chestplate.
    outputs:
      Iron Chestplate:
        amount: 15
        material: IRON_CHESTPLATE
    production_time: 40
  Iron_Helmet:
    inputs:
      Iron Ingot:
        amount: 25
        material: IRON_INGOT
    name: Forge Iron Helmet.
    outputs:
      Iron Helmet:
        amount: 15
        material: IRON_HELMET
    production_time: 25
  Iron_Hoe:
    inputs:
      Iron Ingot:
        amount: 10
        material: IRON_INGOT
    name: Forge Iron Hoe.
    outputs:
      Iron Hoe:
        amount: 30
        material: IRON_HOE
    production_time: 10
  Iron_Leggings:
    inputs:
      Iron Ingot:
        amount: 35
        material: IRON_INGOT
    name: Forge Iron Leggings.
    outputs:
      Iron Leggings:
        amount: 15
        material: IRON_LEGGINGS
    production_time: 35
  Iron_Pickaxe:
    inputs:
      Iron Ingot:
        amount: 15
        material: IRON_INGOT
    name: Forge Iron Pickaxe.
    outputs:
      Iron Pickaxe:
        amount: 15
        material: IRON_PICKAXE
    production_time: 15
  Iron_Spade:
    inputs:
      Iron Ingot:
        amount: 5
        material: IRON_INGOT
    name: Forge Iron Spade.
    outputs:
      Iron Spade:
        amount: 15
        material: IRON_SPADE
    production_time: 5
  Iron_Sword:
    inputs:
      Iron Ingot:
        amount: 10
        material: IRON_INGOT
    name: Forge Iron Sword.
    outputs:
      Iron Sword:
        amount: 15
        material: IRON_SWORD
    production_time: 10
  Iron_XP_Bottle_0:
    inputs:
      Bread:
        amount: 256
        material: BREAD
      Cactus:
        amount: 256
        material: CACTUS
      Carrot:
        amount: 256
        material: CARROT_ITEM
      Glass Bottle:
        amount: 24
        material: GLASS_BOTTLE
    name: Brew XP Bottles  - 1
    outputs:
      Exp Bottle:
        amount: 24
        material: EXP_BOTTLE
  Iron_XP_Bottle_1:
    inputs:
      Baked Potato:
        amount: 256
        material: BAKED_POTATO
      Carrot:
        amount: 256
        material: CARROT_ITEM
      Glass Bottle:
        amount: 14
        material: GLASS_BOTTLE
      Nether Wart:
        amount: 256
        material: NETHER_STALK
    name: Brew XP Bottles  - 2
    outputs:
      Exp Bottle:
        amount: 14
        material: EXP_BOTTLE
  Iron_XP_Bottle_2:
    inputs:
      Bread:
        amount: 64
        material: BREAD
      Cactus:
        amount: 64
        material: CACTUS
      Carrot:
        amount: 128
        material: CARROT_ITEM
      Cocoa:
        amount: 64
        durability: 3
        material: INK_SACK
      Cooked Beef:
        amount: 32
        material: COOKED_BEEF
      Glass Bottle:
        amount: 42
        material: GLASS_BOTTLE
      Pumpkin:
        amount: 64
        material: PUMPKIN
    name: Brew XP Bottles  - 3
    outputs:
      Exp Bottle:
        amount: 42
        material: EXP_BOTTLE
  Iron_XP_Bottle_3:
    inputs:
      Baked Potato:
        amount: 64
        material: BAKED_POTATO
      Cookie:
        amount: 512
        material: COOKIE
      Glass Bottle:
        amount: 42
        material: GLASS_BOTTLE
      Grilled Pork:
        amount: 64
        material: GRILLED_PORK
      Melon Block:
        amount: 64
        material: MELON_BLOCK
      Nether Wart:
        amount: 256
        material: NETHER_STALK
      Sugar Cane:
        amount: 64
        material: SUGAR_CANE
    name: Brew XP Bottles  - 4
    outputs:
      Exp Bottle:
        amount: 42
        material: EXP_BOTTLE
  Produce_Comparators:
    inputs:
      Netherquartz:
        amount: 24
        material: QUARTZ
      Redstone:
        amount: 32
        material: REDSTONE
      Stone:
        amount: 128
        material: STONE
    name: Produce Comparators
    outputs:
      Comparators:
        amount: 32
        material: REDSTONE_COMPARATOR
  Produce_Daylight_Sensors:
    inputs:
      Chest:
        amount: 4
        material: CHEST
      Glass:
        amount: 64
        material: GLASS
      Netherquartz:
        amount: 64
        material: QUARTZ
    name: Produce Daylight Sensors
    outputs:
      Daylight sensors:
        amount: 32
        material: DAYLIGHT_DETECTOR
  Produce_Diamond_Horse_Armor:
    inputs:
      Diamond:
        amount: 64
        material: DIAMOND
    name: Produce Diamond Horse Armor
    outputs:
      Diamond Horse Armor:
        amount: 1
        material: DIAMOND_BARDING
    production_time: 20
  Produce_Dispensers:
    inputs:
      Chest:
        amount: 4
        material: CHEST
      Cobblestone:
        amount: 320
        material: COBBLESTONE
      Redstone:
        amount: 32
        material: REDSTONE
      String:
        amount: 128
        material: STRING
    name: Produce Dispensers
    outputs:
      Dispensers:
        amount: 64
        material: DISPENSER
  Produce_Gold_Horse_Armor:
    inputs:
      Gold Ingot:
        amount: 64
        material: GOLD_INGOT
    name: Produce Gold Horse Armor
    outputs:
      Gold Horse Armor:
        amount: 1
        material: GOLD_BARDING
    production_time: 20
  Produce_Iron_Horse_Armor:
    inputs:
      Iron Ingot:
        amount: 64
        material: IRON_INGOT
    name: Produce Iron Horse Armor
    outputs:
      Iron Horse Armor:
        amount: 1
        material: IRON_BARDING
    production_time: 20
  Produce_Noteblocks:
    inputs:
      Chest:
        amount: 48
        material: CHEST
      Redstone:
        amount: 32
        material: REDSTONE
    name: Produce Noteblocks
    outputs:
      Noteblocks:
        amount: 64
        material: NOTE_BLOCK
  Produce_Pistons:
    inputs:
      Chest:
        amount: 32
        material: CHEST
      Cobblestone:
        amount: 320
        material: COBBLESTONE
      Iron Ingot:
        amount: 128
        material: IRON_INGOT
      Redstone:
        amount: 48
        material: REDSTONE
    name: Produce Pistons
    outputs:
      Pistons:
        amount: 128
        material: PISTON_BASE
  Produce_Powered_Rail:
    inputs:
      Gold Ingot:
        amount: 64
        material: GOLD_INGOT
      Redstone:
        amount: 10
        material: REDSTONE
      Stick:
        amount: 10
        material: STICK
    name: Produce Powered Rails
    outputs:
      Powered Rail:
        amount: 102
        material: POWERED_RAIL
  Produce_Rail:
    inputs:
      Iron Ingot:
        amount: 128
        material: IRON_INGOT
      Stick:
        amount: 32
        material: STICK
    name: Produce Rails
    outputs:
      Rail:
        amount: 528
        material: RAILS
  Produce_Redstone_Torches:
    inputs:
      Redstone:
        amount: 128
        material: REDSTONE
      Stick:
        amount: 128
        material: STICK
    name: Produce Redstone Torches
    outputs:
      Redstone Torches:
        amount: 256
        material: REDSTONE_TORCH_ON
  Produce_Redstone_lamps:
    inputs:
      Glowstone:
        amount: 128
        material: GLOWSTONE
      Redstone:
        amount: 256
        material: REDSTONE
    name: Produce Redstone lamps
    outputs:
      Redstone lamps:
        amount: 128
        material: REDSTONE_LAMP_OFF
  Produce_Repeaters:
    inputs:
      Redstone:
        amount: 128
        material: REDSTONE
      Stone:
        amount: 128
        material: STONE
    name: Produce Repeaters
    outputs:
      Redstone Torches:
        amount: 96
        material: DIODE
  Produce_Saddle:
    inputs:
      Diamond:
        amount: 16
        material: DIAMOND
      Leather:
        amount: 64
        material: LEATHER
      White Wool:
        amount: 64
        material: WOOL
    name: Produce Saddles
    outputs:
      Saddle:
        amount: 8
        material: SADDLE
    production_time: 20
  Produce_Sticky_Pistons:
    inputs:
      Chest:
        amount: 32
        material: CHEST
      Cobblestone:
        amount: 320
        material: COBBLESTONE
      Iron Ingot:
        amount: 128
        material: IRON_INGOT
      Redstone:
        amount: 48
        material: REDSTONE
      Slime Ball:
        amount: 64
        material: SLIME_BALL
    name: Produce Sticky Pistons
    outputs:
      Pistons:
        amount: 128
        material: PISTON_STICKY_BASE
  Smelt_Birch_Wood:
    inputs:
      Birch Wood:
        amount: 256
        durability: 2
        material: LOG
    name: Burn Birch Wood
    outputs:
      Charcoal:
        amount: 512
        durability: 1
        material: COAL
    production_time: 24
  Smelt_Chiseled_Stone_Brick:
    inputs:
      Gravel:
        amount: 64
        material: GRAVEL
      Lapis Lazuli:
        amount: 32
        durability: 4
        material: INK_SACK
      Stone Brick:
        amount: 64
        material: SMOOTH_BRICK
    name: Smelt Chiseled Stone Brick
    outputs:
      Chiseled Stone Brick:
        amount: 64
        durability: 3
        material: SMOOTH_BRICK
    production_time: 64
  Smelt_Coal:
    inputs:
      Coal:
        amount: 256
        material: COAL
    name: Burn Coal
    outputs:
      Charcoal:
        amount: 512
        durability: 1
        material: COAL
    production_time: 24
  Smelt_Coal_Ore:
    inputs:
      Coal Ore:
        amount: 128
        material: COAL_ORE
    name: Smelt Coal Ore
    outputs:
      Coal:
        amount: 384
        material: COAL
    production_time: 12
  Smelt_Cracked_Stone_Brick:
    inputs:
      Flint:
        amount: 64
        material: FLINT
      Lapis Lazuli:
        amount: 32
        durability: 4
        material: INK_SACK
      Stone Brick:
        amount: 64
        material: SMOOTH_BRICK
    name: Smelt Cracked Stone Brick
    outputs:
      Cracked Stone Brick:
        amount: 64
        durability: 2
        material: SMOOTH_BRICK
    production_time: 64
  Smelt_Diamond_Ore:
    inputs:
      Diamond Ore:
        amount: 16
        material: DIAMOND_ORE
    name: Smelt Diamond Ore
    outputs:
      Diamond:
        amount: 48
        material: DIAMOND
    production_time: 1
  Smelt_Glass:
    inputs:
      Sand:
        amount: 256
        material: SAND
    name: Smelt Glass
    outputs:
      Glass:
        amount: 768
        material: GLASS
    production_time: 48
  Smelt_Gold_Ore:
    inputs:
      Gold Ore:
        amount: 32
        material: GOLD_ORE
    name: Smelt Gold Ore
    outputs:
      Gold Ingot:
        amount: 224
        material: GOLD_INGOT
    production_time: 3
  Smelt_Iron_Ore:
    inputs:
      Iron Ore:
        amount: 128
        material: IRON_ORE
    name: Smelt Iron Ore
    outputs:
      Iron Ingot:
        amount: 224
        material: IRON_INGOT
    production_time: 12
  Smelt_Jungle_Wood:
    inputs:
      Jungle Wood:
        amount: 256
        durability: 3
        material: LOG
    name: Burn Jungle Wood
    outputs:
      Charcoal:
        amount: 512
        durability: 1
        material: COAL
    production_time: 24
  Smelt_Lapis_Lazuli_Ore:
    inputs:
      Lapis Ore:
        amount: 32
        material: LAPIS_ORE
    name: Smelt Lapis Lazuli Ore
    outputs:
      Lapis Lazuli:
        amount: 512
        durability: 4
        material: INK_SACK
    production_time: 6
  Smelt_Mossy_Stone_Brick:
    inputs:
      Lapis Lazuli:
        amount: 32
        durability: 4
        material: INK_SACK
      Stone Brick:
        amount: 64
        material: SMOOTH_BRICK
      Vine:
        amount: 64
        material: VINE
    name: Smelt Mossy Stone Brick
    outputs:
      Mossy Stone Brick:
        amount: 64
        durability: 1
        material: SMOOTH_BRICK
    production_time: 64
  Smelt_Nether_bricks:
    inputs:
      Netherrack:
        amount: 512
        material: NETHERRACK
    name: Smelt Nether bricks
    outputs:
      Nether bricks:
        amount: 1024
        material: NETHER_BRICK_ITEM
    production_time: 32
  Smelt_Netherquartz_Ore:
    inputs:
      Netherquartz ore:
        amount: 64
        material: QUARTZ_ORE
    name: Smelt Netherquartz ore
    outputs:
      Quartz:
        amount: 192
        material: QUARTZ
    production_time: 6
  Smelt_Oak_Wood:
    inputs:
      Oak Wood:
        amount: 256
        material: LOG
    name: Burn Oak Wood
    outputs:
      Charcoal:
        amount: 512
        durability: 1
        material: COAL
    production_time: 24
  Smelt_Redstone_Ore:
    inputs:
      Redstone Ore:
        amount: 128
        material: REDSTONE_ORE
    name: Smelt Redstone Ore
    outputs:
      Redstone:
        amount: 1024
        material: REDSTONE
    production_time: 16
  Smelt_Sandstone:
    inputs:
      Sand:
        amount: 512
        material: SAND
    name: Smelt Sandstone
    outputs:
      Sandstone:
        amount: 384
        material: SANDSTONE
    production_time: 32
  Smelt_Spruce_Wood:
    inputs:
      Spruce Wood:
        amount: 256
        durability: 1
        material: LOG
    name: Burn Spruce Wood
    outputs:
      Charcoal:
        amount: 512
        durability: 1
        material: COAL
    production_time: 24
  Smelt_Stone:
    inputs:
      Cobblestone:
        amount: 640
        material: COBBLESTONE
    name: Smelt Stone
    outputs:
      Stone:
        amount: 854
        material: STONE
    production_time: 80
  Wood_XP_Bottle_0:
    inputs:
      Glass Bottle:
        amount: 24
        material: GLASS_BOTTLE
      Wheat:
        amount: 1280
        material: WHEAT
    name: Brew XP Bottles  - 1
    outputs:
      Exp Bottle:
        amount: 24
        material: EXP_BOTTLE
  Wood_XP_Bottle_1:
    inputs:
      Glass Bottle:
        amount: 10
        material: GLASS_BOTTLE
      Nether Wart:
        amount: 1280
        material: NETHER_STALK
    name: Brew XP Bottles  - 2
    outputs:
      Exp Bottle:
        amount: 10
        material: EXP_BOTTLE
  Wood_XP_Bottle_2:
    inputs:
      Baked Potato:
        amount: 1280
        material: BAKED_POTATO
      Glass Bottle:
        amount: 10
        material: GLASS_BOTTLE
    name: Brew XP Bottles  - 3
    outputs:
      Exp Bottle:
        amount: 10
        material: EXP_BOTTLE
  Wood_XP_Bottle_3:
    inputs:
      Cookie:
        amount: 1280
        material: COOKIE
      Glass Bottle:
        amount: 8
        material: GLASS_BOTTLE
    name: Brew XP Bottles  - 4
    outputs:
      Exp Bottle:
        amount: 8
        material: EXP_BOTTLE
  Wood_XP_Bottle_4:
    inputs:
      Carrot:
        amount: 1280
        material: CARROT_ITEM
      Glass Bottle:
        amount: 14
        material: GLASS_BOTTLE
    name: Brew XP Bottles  - 5
    outputs:
      Exp Bottle:
        amount: 14
        material: EXP_BOTTLE
  Wood_XP_Bottle_5:
    inputs:
      Glass Bottle:
        amount: 24
        material: GLASS_BOTTLE
      Hay Bale:
        amount: 143
        material: HAY_BLOCK
    name: Brew XP Bottles  - 6
    outputs:
      Exp Bottle:
        amount: 24
        material: EXP_BOTTLE
