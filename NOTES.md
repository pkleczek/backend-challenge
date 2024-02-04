# Assumptions

Following assumptions were made while working on this challenge:

* There are two million active users who can call this API. The major task of this challenge is to perform network IO.
  Taking that into consideration it seems to be a good idea to select a high throughput as one of the architectural
  drivers.
  Hence, I choose Spring WebFlux to provide better performance under heavy load.
* I've chosen hexagonal architecture to decouple IO from the core behaviour of the application.

# Further notes

* A circuit breaker could be used if the data api is unreliable.
* Also the application could read ETag and cache the API response. 