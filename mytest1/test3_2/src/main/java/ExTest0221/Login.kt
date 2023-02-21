package ExTest0221

import com.example.test3_2.*
import java.util.*


class Login() {
    companion object {
        fun loginTest(user:User){
            if(user.id.equals("admin") && user.pw.equals("1234")) {
                println("로그인 성공")
            }

        }
    }
}

fun main () {

    val scanner = Scanner(System.`in`)
    println("ID:")
    val id = scanner.nextLine()
    println("PW:")
    val pw = scanner.nextLine()
    println("email:")
    val email = scanner.nextLine()
    println("phone:")
    val phone = scanner.nextLine()

    val lsy = User(id,pw,email,phone)

    Login.loginTest(lsy)

}