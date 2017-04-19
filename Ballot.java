import java.util.ArrayList;
class Ballot{
  ArrayList<Candidate> candidates;
  Candidate chosen;
  Ballot(){
    candidates = new ArrayList<Candidate>();

  }
  public boolean vote(String candidateID){
    for(int i=0; i<candidates.size();i++){
      if(candidates.get(i).student.username.equals(candidateID)){
        chosen = candidates.get(i);
        return true;
      }
    }
    return false;
  }
}
