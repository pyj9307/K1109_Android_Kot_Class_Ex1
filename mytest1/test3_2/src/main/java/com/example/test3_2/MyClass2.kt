package com.example.test3_2

//import sun.jvm.hotspot.oops.CellTypeState.value

//최고 상위 영역, 선언과 동시에 초깃값 할당
val name = "이상용"
val name2 :String = "이상용2"
val num1 = 10;

val data4 : Int by lazy {
    println("lazy 테스트")
    10
}
class MyClass2 {
    // 클래스 안에 영역, 선언과 동시에 초깃값 할당
    var name = "이상용3"
    var age = 40;
    val name2 = "이상용4"
}

class User{
    var name = "lsy"
    constructor(name: String){
        this.name = name
    }
    fun someFun() {
        println("name: $name")
    }
}

class User2(val name: String, val age:Int) {
    init {
        println("객체 생성 할 때마다 init 실행이 됨")
    }
}

//class User3(val: String, val age:Int) {
class User3(name:String, age:Int) {
    init {
        println("init 안에서는 주생성자 매개변수 사용 가능 $name, $age")
    }

    // var, val로 지정하면, 다른 함수에서도 사용 가능.
    fun someFun() {
        println("name: $name")
    }
}

class User4(name: String, age: Int, phone: String) {
    // 실제 작업은 주 생성자에서 선언을 해서 사용을 많이 하는 편
//class User4(name: String {
//    constructor(name: String, age: Int):this(name)
//    constructor(name: String, age: Int, phone: String):this(name,age)
}

open class Super(name: String) {
}

class Sub: Super {
    constructor(name: String): super(name) {
    }
}

fun main () {


    val user5 = User3("lsy3",30)
    user5.someFun()

    val user2 = User2("lsy2",30)
    val user3 = User2("lsy3",30)
    val user4 = User2("lsy4",30)

    var myClass2 = MyClass2()
    myClass2.age= 20
    println(myClass2.age)

    // 객체 생성시 new 없이, 바로 생성자 호출.
    val user = User("lsy2")
    user.someFun()

    var data23 = arrayOf<Int> (10,20,30)
    for ((index,value) in data23.withIndex()) {
        print(value)
        if (index !== data23.size -1) print(",")
    }

    var data22 = arrayOf<Int> (10,20,30)
    for (i in data22.indices) {
        print(data22[i])
            if (i !== data22.size -1) print(",")
    }

    var sum22: Int = 0
    for (i in 1..10) {
        sum22 += i}
    println(sum22)


    var data21: Any = "hi"
    val result21 = when (data21) {
        is String -> println("data is String")
        in 1..10 -> println("data is 1..10")
        else -> {
            println("data is not valid")
        }

    }
    println("when 표현식 사용으로 결괏값 확인 $result21")

    var data20: Any = "hi"
    when (data20) {
        is String -> println("data is String")
        in 1..10 -> println("data is 1..10")
        else -> {
            println("data is not valid")
        }
    }

    var data19 = "hi"
    when (data19) {
        "hi" -> println("data is hi")
        "hi2" -> println("data is hi2")
        else -> {
            println("data is not valid")
        }
    }

    var data = 10
    var result = if(data>0) {
        println("테스트")
        true
    } else {
        println("else 테스트")
        false
    }
    println("result 결괏값 테스트 : $result")

    // 가변 길이의 리스트, 맵
    val data18 = mutableMapOf<String, Any>()
    data18.set("key","value")
    data18.set("key2",2)
//    data18.set("key3",MyClass2)
    println(data18.get("key"))
    println(data18.get("key2"))

    val data17 = mutableListOf<Int>()
    data17.add(1)
    data17.add(2)
    println(data17[0])

    val data15 = intArrayOf(10,20,30)
    val data16 = booleanArrayOf(true,false)
    println(
        """
    array size : ${data15.size}
    array data : ${data15[0]}, ${data15[1]}, ${data15.get(2)}
            """
    )
    println(
            """
    array size : ${data16.size}
    array data : ${data16[0]}, ${data16[1]}
            """
            )


    val data14: Array<Int> = Array(3,{0})
    data14[0] = 10
    data14[1] = 10
    data14.set(2,30)

    println(
        """
    array size : ${data14.size}
    array data : ${data14[0]}, ${data14[1]}, ${data14.get(2)}
            """
    )
    
    fun some (data1: Int, data2: Int = 10) : Int {
        return data1 * data2
    }
    println(some(data1=100, data2=200))

    fun some2(test: Int, test2: Int): Nothing {
        throw java.lang.Exception()
    }

    var n1 : Int?
    n1 = null

    var data13 : Nothing? = null

    var data12: Any = 10
    var data2: Any = "String"
    var data3: Any = MyClass2()

    fun test3() {
        println(data12)
        println(data2)
        println(data3)
    }
    var testxx = test3()
    println(testxx)


    fun addSum(no: Int): Int {
        var sum = 0
        for (i in 1..no){
            sum += i
        }
        return sum
    }

    val name = "yong"
    println("name: $name, sum: ${addSum(10)}")

    val str1 = "hi \n yong"
    val str2 = """
        hi
        world
        """
    println("str1: $str1")
    println("str2: $str2")

    var data1: Int = 10
    data1 = data1 + 10
    data1 = data1.plus(10)

    // 함수 내부에서는 선언만 가능.
    var name10 : String
    // 자바에서 쓰던 Myclacss2 myclass2 = new Myclass2(); 을
    var myclass2 = MyClass2()
    // myclass2.name2 = "이상용5" - val 은 재할당 안됨.
    myclass2.name = "이상용5"
    println("hello World")
    println(myclass2.name)
    println(myclass2.age)
    println(myclass2.name2)
    println(name2)
    println("lazy 테스트 및 결괏값 재할당해서 연산 확인 : ")
    // data4는 최상단에 by lazy 적용
    println(data4+10)
}