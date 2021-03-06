
# Parliament App

#### App Video Demo: https://www.youtube.com/watch?v=hljNXxWsULY&list=FL6h_p8w2vQeWDL267vrtd9A&index=1

#### An Android App shows the list of members in Parliament using Kotlin. The app includes different views:

- View 1: Title of the App
- View 2: List of parties 
- View 3: List of members of each party
- View 4: Member's details
- View 5: Show feedback of a member

#### Project main concepts:

- App views are implemented as fragments and use navigation mechanism to move from a view to another.
- View model is used for each view and with binding mechanism.
- Displaying collections of information with RecyclerView.
- Live data is used to update views from database.
- Coroutin is for handling data in asynchronous way.
- Data is fetched from the internet (https://users.metropolia.fi/~peterh/mps.json) using retrofit and moshi libraries and then stored in Room database of the device.
