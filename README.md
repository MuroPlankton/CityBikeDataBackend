# CityBikeDataBackend
Java Spring based backend for a simple application which allows the user to view the contents of a database of city bike stations and trips.

## How to run:

 - Clone this repository and import the Maven project into your IDE (I used IntelliJ).
 - Download the required data csv files from [the first](https://dev.hsl.fi/citybikes/od-trips-2021/2021-05.csv), [second](https://dev.hsl.fi/citybikes/od-trips-2021/2021-06.csv), [third](https://dev.hsl.fi/citybikes/od-trips-2021/2021-07.csv) and [fourth link](https://opendata.arcgis.com/datasets/726277c507ef4914b0aec3cbcfcbfafc_0.csv).
 - Move the downloaded files into the csvFiles folder located at the content root of the project.
 - Set up a MariaDB server on the same device or some other place that can be accessed by this program.
 - Add your datasource URL and credentials to the application.properties files located at src/main/resources/ from content root.
 - Now you should be able to run the program with Maven goal "CityBikeDataBackend: spring-boot:run".
 
## Functionality:

 - When calling GET on /station/ and /trip/ you should get lists of stations and lists nicely paginated.
 - Pages can be selected with the "page" query parameter.
 - Size of the pages can be changed with the "limit" query parameter.
 - Stations can be added with a POST to /station/ where the body has the same fields as the stations that can be retreived with a GET request.
 - Trips can be added in a similar fashion to stations but instead of including the full station objects in the body the endpoint expects the station IDs in "departure_station" and "return_station" parameters.
