# Change Log

All notable changes to the "leathered-boots" Minecraft mod will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Crystal Nest Semantic Versioning](https://crystalnest.it/#/versioning).

## [Unreleased]

- Port to 1.21.6.

## [v6.1.0] - 2025/06/22

- Port to 1.21.5.

## [v6.1.0] - 2025/02/05

- 1.21.3 and above only.
- Drastically increased the chance of finding the Leather Upgrade Smithing Template.
- Increased the chance of finding Leathered Golden Boots, Leathered Chain Boots, Leathered Iron Boots, and Leathered Diamond Boots.
- Added a 1 in a 1000 chance of finding Netherite Leathered Boots.
- Changed the duplication crafting for Leather Upgrade Smithing Template from using 7 diamonds to using 7 iron nuggets.

## [v5.1.0] - 2025/02/05

- 1.21 and 1.21.1 only.
- Drastically increased the chance of finding the Leather Upgrade Smithing Template.
- Increased the chance of finding Leathered Golden Boots, Leathered Chain Boots, Leathered Iron Boots, and Leathered Diamond Boots.
- Added a 1 in a 1000 chance of finding Netherite Leathered Boots.
- Changed the duplication crafting for Leather Upgrade Smithing Template from using 7 diamonds to using 7 iron nuggets.

## [v6.0.2] - 2025/02/02

- Ported to 1.21.4.

## [v6.0.2] - 2024/12/20

- 1.21.3 and above.
- Fix registration of new leathered boots forcing `leathered_boots` as the mod ID for equipment models and textures.

## [v6.0.1] - 2024/12/14

- 1.21.3 only.
- Add compatibility for Cobweb 1.3.0.

## [v5.0.1] - 2024/12/14

- 1.21 and 1.21.1 only.
- Add compatibility for Cobweb 1.3.0.

## [v6.0.0] - 2024/12/08

- Ported to 1.21.3.

## [v5.0.0] - 2024/11/14

- Added support for 1.21.1.

## [v5.0.0] - 2024/07/14

- Ported to 1.21.
- Officially dropped support for Forge.
- Cobweb minimum version is now `v1.1.2`.
- Renamed data folder from plural to singular to comply with the new Minecraft standard.
- Added Soft Step as a data driven enchantment.
- Added every leathered boots registered via the API to Minecraft `dyeable` and `foot_armor` item tags too (before they were only in `trimmable_armor` and `freeze_immune_wearables`).
- `BiomesCheck` and `BiomesPredicate` are now both `record`s rather than a final classes.
- `LeatheredArmorMaterial` has been removed due to changes in Minecraft `ArmorMaterial`.
- `LeatheredBootsItem` now take in a `Holder` for the `ArmorMaterial` and a durability factor multiplier.
- `LeatheredBootsItem` now extend `ArmorItem`.
- Changed how `LeatheredBootsItem` are registered: first an instance of `BootsRegister` must be created with `LeatheredBootsManager#register(String)` passing a mod ID, then register each pair of boots one at a time with it.
- A `ResourceLocation` is now required to get a `LeatheredBootsItem` from the `LeatheredBootsManager` instead of a `LeatheredArmorMaterial`.  

## [v4.0.0] - 2024/07/04

- Update to the newest standards.
- Add more language translations.
- Change mod ID from `leatheredboots` to `leathered_boots`.
- Update API with improvements and breaking changes.
- API now uses provided mod IDs rather than always `leathered_boots`.
- Fabric only: add support for [Detail Armor Bar](https://github.com/RedLime/DetailArmorBar) mod.

<details>
  <summary>Legacy</summary>

### [1.20.4-3.0.0.0] - 2023/12/17
- Port to 1.20.4.

### [1.20.2-3.0.0.0] - 2023/12/14
- Port to 1.20.2.

### [1.20.1-3.0.0.0] - 2023/12/13
- Port to 1.20.1.
- Added the leather upgrade smithing template to upgrade boots to their leathered version.
- Added the leather upgrade smithing template to the loot of some structures (Igloos, Pillager Outposts, Shipwrecks, Trail Ruins) in snowy or frozen biomes.
- Added duplication crafting for the leather upgrade smithing template.
- Replaced previous crafting and smithing recipes for leathered boots with new smithing only recipes using the new smithing template.
- Integrated armor trims with leathered boots.

### [1.19.4-3.0.0.0] - 2023/12/01
- Port to 1.19.4.

### [1.19.3-3.0.0.0] - 2023/11/28
- Finalized changes from [1.19.3-3.0.0.0-beta].
- Removed mod id parameters from the API methods.
- Fixed a minor bug in leathered boots registration.
- Added override for the `getMaterial()` method in `LeatheredBootsItem`.

### [1.19.2-3.0.0.0] - 2023/11/28
- Finalized changes from [1.19.3-3.0.0.0-beta].
- Removed mod id parameters from the API methods.
- Fixed a minor bug in leathered boots registration.
- Added override for the `getMaterial()` method in `LeatheredBootsItem`.

### [1.18.2-3.0.0.0] - 2023/11/28
- Finalized changes from [1.19.3-3.0.0.0-beta].
- Removed mod id parameters from the API methods.
- Fixed a minor bug in leathered boots registration.
- Added override for the `getMaterial()` method in `LeatheredBootsItem`.

### [1.19.3-3.0.0.0-beta] - 2023/11/25
- Added API.
- Added smithing recipes for all leathered boots.
- Fixed some Vanilla advancements to include leathered boots.
- Changed all item and translation keys.
- Added Soft Step enchantment.

### [1.19.3-2.0.0.0] - 2023/01/02
- Ported to 1.19.3.

### [1.19.2-1.0.0.2] - 2022/10/24
- Fixed golden leathered boots not making piglins neutral.

### [1.18.2-1.0.0.2] - 2022/10/24
- Fixed golden leathered boots not making piglins neutral.

### [1.19.2-1.0.0.1] - 2022/10/23
- Added smithing recipe to craft diamond leathered boots into netherite leathered boots.

### [1.18.2-1.0.0.1] - 2022/10/23
- Added smithing recipe to craft diamond leathered boots into netherite leathered boots.

### [1.19.2-1.0.0.0] - 2022/10/21
- Added leathered boots for each Vanilla boots apart from leather boots.
- Leathered boots prevent freezing and allow walking on powdered snow.
- Leathered boots can be dyed with a dye and undyed with a cauldron.
- Added villager trades for leathered boots.
- Modified Vanilla loot tables for igloos and snowy village houses.
- Drafted some code for future compatibility with Revamped End and Gilded Armors.
- Added craftings for leathered boots.

### [1.18.2-1.0.0.0] - 2022/10/20
- Added leathered boots for each Vanilla boots apart from leather boots.
- Leathered boots prevent freezing and allow walking on powdered snow.
- Leathered boots can be dyed with a dye and undyed with a cauldron.
- Added villager trades for leathered boots.
- Modified Vanilla loot tables for igloos and snowy village houses.
- Drafted some code for future compatibility with Revamped End and Gilded Armors.
- Added craftings for leathered boots.
</details>

[Unreleased]: https://github.com/crystal-nest/leathered-boots
[README]: https://github.com/crystal-nest/leathered-boots#readme

[v6.1.0]: https://github.com/crystal-nest/leathered-boots/releases?q=6.1.0
[v6.0.2]: https://github.com/crystal-nest/leathered-boots/releases?q=6.0.2
[v6.0.1]: https://github.com/crystal-nest/leathered-boots/releases?q=6.0.1
[v6.0.0]: https://github.com/crystal-nest/leathered-boots/releases?q=6.0.0
[v5.1.0]: https://github.com/crystal-nest/leathered-boots/releases?q=5.1.0
[v5.0.1]: https://github.com/crystal-nest/leathered-boots/releases?q=5.0.1
[v5.0.0]: https://github.com/crystal-nest/leathered-boots/releases?q=5.0.0
[v4.0.0]: https://github.com/crystal-nest/leathered-boots/releases?q=4.0.0

[1.20.4-3.0.0.0]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.20.4-3.0.0.0

[1.20.2-3.0.0.0]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.20.2-3.0.0.0

[1.20.1-3.0.0.0]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.20.1-3.0.0.0

[1.19.4-3.0.0.0]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.19.4-3.0.0.0

[1.19.3-3.0.0.0]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.19.3-3.0.0.0
[1.19.3-3.0.0.0-beta]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.19.3-3.0.0.0-beta
[1.19.3-2.0.0.0]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.19.3-2.0.0.0

[1.19.2-3.0.0.0]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.19.2-3.0.0.0
[1.19.2-1.0.0.2]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.19.2-1.0.0.2
[1.19.2-1.0.0.1]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.19.2-1.0.0.1
[1.19.2-1.0.0.0]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.19.2-1.0.0.0

[1.18.2-3.0.0.0]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.18.2-3.0.0.0
[1.18.2-1.0.0.2]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.18.2-1.0.0.2
[1.18.2-1.0.0.1]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.18.2-1.0.0.1
[1.18.2-1.0.0.0]: https://github.com/crystal-nest/leathered-boots/releases/tag/v1.18.2-1.0.0.0
