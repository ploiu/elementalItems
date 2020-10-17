# About
Elemental Items is a mod that adds a bunch of strong weapons, tools, and armor to Minecraft. Most of the items have an obvious link to a corresponding element (Fire, Ice, Water, Nature, Earth, and Air), while others are based off of a purely Minecraftian concept (such as Ender). One sword is based off of the 2013 artwork for Magic: The Gathering's Sword of Light and Shadow, and while it doesn't fit any sort of elemental theme, I really liked it when I first wrote this mod back in 2013. 

Now That we've got that out of the way, let's take a look at what we got. 
<hr />

## Fire

Probably the first concept I wanted to exist in Minecraft that drove me to create this mod in the first place. While not very enchantable (have you ever _tried_ to tame fire?), wielding items imbued with fire allow you to light your enemies aflame as well as automatically smelt anything you mine. Not to mention that wearing a full set of fire armor allows you to see perfectly fine while submerged in lava, on top of always being immune to fire. 

## Ice

The second concept that I wanted to add. Ice is a very pristine, crystalline, and beautiful element. Items imbued with ice have a magical fidelity even higher than that of gold, and allow you to enchant them with powerful enchantments with more often and more reliable than with any other material in vanilla Minecraft and this mod. On top of being magically inclined, ice armor will automatically slow and weaken those who wish harm upon the wearer, and the same fate will happen to any unlucky enough to taste the blade of an ice sword. Though powerful in its abilities, ice is very brittle and will break much more easily than the other elements.

## Water

Water is a force to be reckoned with. One wrong move in a body of water can whisk anything away from shore, and into the dark abyss. Items imbued with water are faster than any vanilla item. The tools break most blocks almost instantly, and the sword will push anyone away when they are struck by it. Water arrows are not bothered by water like their non-water counterparts, and sail effortlessly through it as if it weren't there. Those who choose to wear water armor are completely immune to explosions, and a full set will grant the wearer full mobility, sight, and air while underwater. 

## Nature

Nature has always been viewed in literature as something to learn from. Nature is strong and resilient. While a forest may succumb to the flames of an all-consuming fire, youthful trees grow back in their place and restore the bountiful green that once preceded them. Users of leaf-imbued tools will grow with their use; earning experience from the tools when they are used to mine the correct blocks. Those who wear leaf armor are immune to any form of withering effects, and those who wear a full set gain a permanent regeneration effect. Nature hates the undead, and any undead monster that attacks a wearer of leaf armor, or feels the bite of a leaf blade, will take extra damage and quickly be laid back to rest.

## Earth

The earth is resilient. The earth is strong. The earth is shamed by what stone swords are. While having no obvious magical abilities like the other sets, items imbued with earth are solid and reliable. Earth tools and weapons have a durability that far outstrip any normal item in Minecraft. While the sword is heavy and slow, it has the power to smash any living thing back to the ground if it tries to stay in the air, and it will bury any mob unfortunate enough to be on the ground when it is struck. Earth armor is heavy, and wearing a full set makes the wearer immune to knockback by normal means.

## Air

Air is everywhere. It is a part of this planet and we rely on it to survive. The human race has longed to fly with the birds for millennia. Wearing a full set of air armor will allow the wearer to break the hold of gravity upon them and fly as high as they wish. Using an air sword allows the wielder to force their enemies to experience the same experience -- but only momentarily before they are claimed again by the earth and plummet straight to the ground. Those who attack a wearer of air armor are pushed away by strong winds and must reach their target again to do any more damage.

## Ender

This is the only element exclusive to Minecraft. The end is not just an element -- it is an entirely separate dimension with its own inhabitants. Arguably the most versatile, Tools imbued with the ender element will automatically teleport broken blocks to the user's inventory. Broken containers like chests will also have their contents teleported to the breaker's inventory as well. If the wielder's inventory is full, the remaining items are placed at their feet. The ender sword disorientingly-teleports any living thing hit to a random, nearby location. Any wearer of ender armor will grow in power the more pieces are worn, with lasting effects including night vision and luck being granted to those who use the armor to the fullest. 

<hr />
How'd you like those descriptions? I came up with them myself, pretty cool right? Here's a quick rundown of the base numbers for each elemental set:

Element | Durability | Mining Speed | Attack Damage | Enchantability
--------|------------|--------------|---------------|-------------------
Fire       |2000|8 |5 |5
Ice        |1000|8 |5 |50
Water      |2000|50|5 |10
Leaf       |2000|8 |15|15
Earth      |4000|8 |5 |10
Air        |2000|10|5 |10
Ender      |2000|8 |10 |22
Plain      |2000|8 |5 |10
Vs Diamond |1561|8 |3 |10

Numbers aren't everything though -- the special effects listed above the table make some elements with lower numbers (like fire) perfectly viable because of the extra benefits they give. 

## Known bugs
With every piece of software, there are bugs. As of this writing, there are a few that I know of that I will list down below. The ones that are italicized can _technically_ be fixed, but I will choose not to because of what would need to be done to fix them. I will provide a description as to why the bug exists.

* _The ender dragon ignores the knockback immunity the earth set provides_
  - Exists because of the way Minecraft Forge, the modding api I used to create this mod, works. The ender dragon doesn't technically apply a knockback effect. When the dragon runs into a living thing, it explicitly sets the velocity of that living thing instead of applying a knockback effect. There is no guaranteed way to prevent this from happening without potentially introducing weird bugs with knockback.
