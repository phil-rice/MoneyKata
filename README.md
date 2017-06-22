# Money Kata
 
How do we implement money?
 
## Step 1:
 
Today’s task is to write a class for ‘Money’ that allows the following operations:
* We can add up money
* We can split money up into N equal sized chunks, and allocate the remainder in some way (example £10 split three ways means three lots of £3.33 and a single 0.01p left over, and this
 
Unfortunately the client isn't sure how money will be represented yet. Probably it will be a single currency, but it
might be a BigInt, a BigDecimal, or just a simple Long.

## Step 2:
 
It turns out that finance want all money operations logged. No one has quite decided what logging framework we are going to be using yet
 
## Step 3:
 
We now want to know how many times the money operations are called
 
## Step 4:
 
Our customer has a client with $100 trillion US dollars, and we may need to split that money without losing a cent.

## Step 5:
We want a ‘brag statistic’: We want a sum of all the money that we have added up and split
 
