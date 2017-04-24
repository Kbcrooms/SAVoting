import java.util.ArrayList;
class Ballot{
  ArrayList<Candidate> candidates;
  Candidate chosen;

  //Structured As Results = {RawCount, Winner, TunoutStats}
  ArrayList<Boolean> results;

  //Structured As Eligibility = {Rank1,Rank2,Rank3,Rank4,Rank5,Rank6,
  //                             College1, College2, Club}
  ArrayList<Boolean> eligibility;

  //These two arrayLists will be in the same order. raceNames[1] is the same race as raceCandidates[1]
  //where raceNames contains the name of the race and raceCandidate[1] contains another arraylist of all
  //Candidates for that race.
  ArrayList<Integer> numOfCandidates;
  ArrayList<ArrayList<Candidate>> raceCandidates; 
  ArrayList<String> raceNames;
  int currentNumberOfCandidates;

  int numOfRaces;
  //int numOfCandidates;

  Ballot(){
    numOfCandidates = new ArrayList<Integer>();
    candidates = new ArrayList<Candidate>();
    raceCandidates = new ArrayList<ArrayList<Candidate>>();
    raceNames = new ArrayList<String>();
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
