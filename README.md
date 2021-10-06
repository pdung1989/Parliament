
# Parliament App
#### An Android App shows the list of members in Parliament using Kotlin. The app includes different views:

- View 1: Title of the App
- View 2: List of members
- View 3: Member's details

#### Project main concepts:

- App views are implemented as fragments and use navigation mechanism to move from a view to another.
- View model is used for each view and with binding mechanism.
- Displaying collections of information with RecyclerView.
- Live data is used to update views from database.
- Data is fetched from the internet (https://users.metropolia.fi/~peterh/mps.json) using retrofit and moshi libraries and then stored in Room database of the device.
