# BazaarPriceUpdater
Update Bazaar Prices

Start by installing a Java IDE and get that setup.  Then just run this project and hit the button to modify your toon's buyer and seller prices.  The only bit of code you have to change is to update the buyerFilePath and sellerFilePath variables (in src/GUI) with your own filepaths to your characters' BART_charname_server.ini and BZR_Charname_server.ini.  The Raise Buyer Prices button currently raises all prices under 50 by 1 plat, under 100 by 2 plat, under 400 by 4 plat, and under 1.8 mil by 5 plat(just to avoid attempting to go over 2 mil).  The Lower Seller Prices button currently lowers all item prices less than 1,999,999 and greater than 10 by 1%.  Keep in mind this will lower all prices your character has a price set for, including ones that have already sold.

These may get buggy with gold/silver/copper in your auctions.  I can't log in to see what effect those values have on the parts of the log files I'm working with are atm.
