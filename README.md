# backend-challenge

Coding challenge: Create a small web service that takes location and a search query as optional parameters and returns sorted and filtered results.

# Scope
- Use common libraries (for DI, networking etc.) to structure your app. 
- Get location data from https://payback-coding-challenge.s3.eu-central-1.amazonaws.com/germany.json
- Parse location and search query from request parameters (use 0,0 if not provided).
- Filter the list based on the search string and return the list of places sorted by distance from the location.
- List items show the name of an item, the address and the distance to the current location.
- Write a unit test for one component of the application.


# Some starting point for discussion
- What makes your solution an efficient implementation? 
- What and how would you test your code?
- How would you handle a change of the data source, e.g. a different data format?
- How would you secure the web service? 

# Delivery
Fork this repository and make your solution publicly available. 
