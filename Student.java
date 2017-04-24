class Student{
  String username;
  String password;
  String major;
  String rank;
  String college;
  String name;
  Student(String username, String name){
    this.username = username;
    this.name = name;
  }

  Student(  String username,String password,String major,String rank,String college){
    this.username = username;
    this.password = password;
    this.major = major;
    this.rank = rank;
    this.college = college;
  }

}
