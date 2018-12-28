package com.appetiser.itunessearch.data
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.parceler.Parcel

/**
 * The full response object for the url https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie
 */

data class SearchResponse(
    @SerializedName("resultCount")
    var resultCount: Int,
    @SerializedName("results")
    var results: List<ResultObject>
)

@Parcelize
data class ResultObject(
    @SerializedName("artistId")
    var artistId: Int,
    @SerializedName("artistName")
    var artistName: String,
    @SerializedName("artistViewUrl")
    var artistViewUrl: String,
    @SerializedName("artworkUrl100")
    var artworkUrl100: String,
    @SerializedName("artworkUrl30")
    var artworkUrl30: String,
    @SerializedName("artworkUrl60")
    var artworkUrl60: String,
    @SerializedName("artworkUrl600")
    var artworkUrl600: String,
    @SerializedName("collectionArtistId")
    var collectionArtistId: Int,
    @SerializedName("collectionArtistName")
    var collectionArtistName: String,
    @SerializedName("collectionArtistViewUrl")
    var collectionArtistViewUrl: String,
    @SerializedName("collectionCensoredName")
    var collectionCensoredName: String,
    @SerializedName("collectionExplicitness")
    var collectionExplicitness: String,
    @SerializedName("collectionHdPrice")
    var collectionHdPrice: Double,
    @SerializedName("collectionId")
    var collectionId: Int,
    @SerializedName("collectionName")
    var collectionName: String,
    @SerializedName("collectionPrice")
    var collectionPrice: Double,
    @SerializedName("collectionViewUrl")
    var collectionViewUrl: String,
    @SerializedName("contentAdvisoryRating")
    var contentAdvisoryRating: String,
    @SerializedName("copyright")
    var copyright: String,
    @SerializedName("country")
    var country: String,
    @SerializedName("currency")
    var currency: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("discCount")
    var discCount: Int,
    @SerializedName("discNumber")
    var discNumber: Int,
    @SerializedName("feedUrl")
    var feedUrl: String,
    @SerializedName("genreIds")
    var genreIds: List<String>,
    @SerializedName("genres")
    var genres: List<String>,
    @SerializedName("hasITunesExtras")
    var hasITunesExtras: Boolean,
    @SerializedName("isStreamable")
    var isStreamable: Boolean,
    @SerializedName("kind")
    var kind: String,
    @SerializedName("longDescription")
    var longDescription: String,
    @SerializedName("previewUrl")
    var previewUrl: String,
    @SerializedName("primaryGenreName")
    var primaryGenreName: String,
    @SerializedName("releaseDate")
    var releaseDate: String,
    @SerializedName("shortDescription")
    var shortDescription: String,
    @SerializedName("trackCensoredName")
    var trackCensoredName: String,
    @SerializedName("trackCount")
    var trackCount: Int,
    @SerializedName("trackExplicitness")
    var trackExplicitness: String,
    @SerializedName("trackHdPrice")
    var trackHdPrice: Double,
    @SerializedName("trackHdRentalPrice")
    var trackHdRentalPrice: Double,
    @SerializedName("trackId")
    var trackId: Int,
    @SerializedName("trackName")
    var trackName: String,
    @SerializedName("trackNumber")
    var trackNumber: Int,
    @SerializedName("trackPrice")
    var trackPrice: Double,
    @SerializedName("trackRentalPrice")
    var trackRentalPrice: Double,
    @SerializedName("trackTimeMillis")
    var trackTimeMillis: Int,
    @SerializedName("trackViewUrl")
    var trackViewUrl: String,
    @SerializedName("wrapperType")
    var wrapperType: String
) : Parcelable