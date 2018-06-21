# rocket-league-stats: A Kotlin wrapper for RLStats public API

### Usage examples
##### Simplest usage: finding Kuxir97's stats, the real one.
###### Declare properties for Retrofit Service the built-in client relies to:
* *requests*: maximum number of addressable requests per time interval
* *interval*: (see previous)
* *apiKey*: key to provide at every API request
* *apiRootUrl*: API server URL
```Kotlin
val props = RetrofitServiceProperties(
        requests = 1,
        interval = TimeSpan(500, TimeUnit.MILLISECONDS),
        apiKey = key,
        apiRootUrl = "https://api.rocketleaguestats.com/"
)
```
It can be read as follows:
> This service is located in *apiRootUrl* and I can use this service providing this *apiKey*. I can issue **at most** *requests* every *interval*.
###### Create a built-in client instance using the declared properties
```Kotlin
val client = RLStatsAPIClient.createFromProps(props)
```
###### Use it for retrieve some players' information
```Kotlin
val players: Collection<Player> = client.findPlayers("Kuxir97") {
    it.stats.goals > 38000 //Kuxir is a god, he scored LOADS of goals
}
```
---
##### Retrieve only enumerable data information
###### Get a RLService for the built-in RLDataProvider implementation
`IndexedRLDataProvider` implements `RLDataProvider` and depends only on the RLService interface, representing a Retrofit description of the RLStats REST API, you can retrieve it:
* either directly using Retrofit
```Kotlin
val service: RLService = Retrofit.Builder()
                .baseUrl(apiRootUrl)
                .addConverterFactory(JacksonConverterFactory.create()) //RLService depends on Jackson for JSON serialization
                .client(gimmeClient()) //A OkHttpClient can be set in order to intercept every Retrofit request in order to add the ApiKey or limiting requests
                .build()
                .create(RLService::class.java)
```
* or using `RetrofitServiceProperties` extension functions in order to instantiate an RLService
```Kotlin
val service: RLService = RetrofitServiceProperties(
        requests = 2,
        interval = TimeSpan(1, TimeUnit.SECONDS),
        apiKey = key,
        apiRootUrl = "https://api.rocketleaguestats.com/"
).generateRLService()
```
###### Instantiate IndexedRLDataProvider
**Warning**: `IndexedRLDataProvider` eagerly loads every information during instantiation
```Kotlin
val provider: RLDataProvider = IndexedRLDataProvider(service)
```
###### Use that instance to find enumerable information provided by RLStats REST API
```Kotlin
val platforms: Collection<Platform> = provider.allPlatforms() //Retrieves all the platforms
val platform: Platform? = provider.getPlatform(1) //Retrieves platform with id = 1
val tiers: Collection<Tier> = provider.searchTiers("Gold") //Retrieves every tier with name containing "Gold"
```
