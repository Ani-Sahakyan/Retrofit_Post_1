package am.example.retrofit_post_1

class UserModel {

    var data : List<UserData>? = null

   data class UserData(
        var id : Int?,
        var title : String?,
        var url : String?
   )
}