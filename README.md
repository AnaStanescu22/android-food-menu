# Android-mvvm-food-menu

This sample Kotlin app retrieves a list of food categories from an API with Retrofit. The data is returned in JSON format and GSON deserialized.
The app displays the content with `RecyclerView` and uses a traditional adapter.

The architecture is MVVM where the `FoodCategoriesViewModel` is in charge of preparing and altering the UI state for the `FoodCategoriesActivity`. The activity listens for state changes through LiveData observing.
The data is retrieved in the `FoodMenuRepository` while all async operations are done through Coroutines.

For image loading, the app uses Picasso. 

![App presentation pattern](https://i.imgur.com/QZUZ1LZ.png)
