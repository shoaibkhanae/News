package drawable

@GET("top-headlines")
    suspend fun getNews(
        @Query("country") countryCode: String = "us",
        @Query("apikey") apiKey: String = AppConstants.API_KEY
    ): Response<News>