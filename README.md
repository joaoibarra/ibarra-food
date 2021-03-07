# ibarra-food

Ibarra Food is a small app that uses a mock api provide by https://designer.mocky.io. I used the MVVM architecture with Koin dependency injection. Also, I created tests using Espresso and MockK.

### Checkout the project ###

For checkout the project, just clone the repo address:

```git clone https://github.com/joaoibarra/ibarra-food.git```

### What this project do ###
Currently the project have some features:
- A list with restaurants;
- A search field (Find a restaurant by name);
- Add a restaurant to a favorite list;
- Some Unit and UI tests with a mock webserver.

### What's next ###
- Implement search filters;
- Improve all tests(Mainly idle state for Fragment);
- Improve layout and interactions;
- Used Room for database and Retrofit with a mock API, so will be easy to connect with any API or create a pagination;
- Improve CI adding more tasks to Github Actions

### Run tests ###
Its possible to run unit tests directly in IDEA or using the command below:

``` ./gradlew test```

And for UI tests:

``` ./gradlew connectedAndroidTest```
