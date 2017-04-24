class Election{
  String eName;
  String eComID;
  String sDate;
  String eDate;
  Ballot ballot;
  Election(){};
  Election(String eName, String eComID,String sDate,String eDate){
    this.eName = eName;
    this.eComID = eComID;
    this.sDate = sDate;
    this.eDate = eDate;
  }

}
