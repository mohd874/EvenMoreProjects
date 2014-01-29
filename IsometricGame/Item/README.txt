ITEMS:

There are three categories of items:  species, model, and instance.  All
instances of an item have a model of item which governs all the variables that 
are fixed for every instance of that type.  For example, all UZIs will have the 
same base rate of fire, so the UZI model stores the rate-of-fire.  Every type
has a species which defines the basic genre of an item (RangedWeapon,
MeleeWeapon, Medkit, etc.).  The species defines the actual object that is
created when an instance of the item is created.

Instead of having a fixed image like everything else, the items all draw from
a massive itemIage, the main item image should be a grid of 50x50 cells,
and item images are defined by the rectangular group of cells that is 
specified.

The SpriteCreator class is an application that will create items for use in
the game.  Currently, the SpriteCreator class sves the items as objects; however
it can also be used with a DataOutputStream and using the .send() function built
into the items.
