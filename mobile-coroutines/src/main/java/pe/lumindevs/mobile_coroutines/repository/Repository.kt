package pe.lumindevs.mobile_coroutines.repository

/** Repository is an interface for configuring base repository classes. */
interface Repository {
    //this override property is for saving network loading status.
    var isLoading: Boolean
}