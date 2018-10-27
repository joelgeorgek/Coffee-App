# MyCoffeeApp
A basic Android application which implements a simple system to order coffee.

# What can the app do ?
Lets you place and remove orders for different varieties of coffees and
calculates the cost depending upon the type of coffee and the number of coffees ordered.

# How does the App work ?
The launcher activity consists of a recycler view which displays all the orders.
On clicking the add icon in the action bar, it opens a new activity where you can order your coffees.
After entering your order and the count of items, then on clicking the 'PLACE ORDER' button, it adds your order in an Sqlite database,
calculates the price and displays your order on the recycler view in the Launcher activity.
Each item in the recycler view displays the name of the item, its count and its cost along with a button to remove an order which
removes the order from the sqlite database.
