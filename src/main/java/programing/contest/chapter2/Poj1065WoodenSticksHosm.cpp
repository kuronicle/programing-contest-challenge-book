/**
  Wooden Sticks
  http://poj.org/problem?id=1065
 */
#include <iostream>
#include <iterator>
#include <vector>
#include <utility>
#include <algorithm>
#ifndef ONLINE_JUDGE
#include <deque>
#endif //ONLINE_JUDGE

using namespace std;

typedef pair<int, int> Stick;
typedef vector<Stick> Sticks;

#ifdef ONLINE_JUDGE
class StickGroup {
public:
    Stick largest;
    StickGroup() : largest(Stick(0,0)){}
    StickGroup(Stick s) : largest(s){}
};
#else
class StickGroup : public deque<Stick> {
public:
    Stick largest;
    StickGroup() : largest(Stick(0,0)){}
    StickGroup(Stick s) : largest(s){ this->push_back(s); }
};
#endif //ONLINE_JUDGE

typedef vector<StickGroup> StickGroups;

struct StickGroupComparator {
    bool operator()(const StickGroup& sg1, const StickGroup& sg2){
        return sg1.largest.second < sg2.largest.second;
    }
};    

// for debug
#ifndef ONLINE_JUDGE
static inline void print_stick(const Stick& s) {
    cerr << " (" << s.first << ", " << s.second << ")";
}
static inline void print_stickgroup(const StickGroup& sg) {
    cerr << "*"; print_stick(sg.largest); cerr << ":["; 
    for_each(sg.begin(), sg.end(), print_stick);
    cerr << " ]" << endl;
}
static inline void print_stickgroups(const StickGroups& sgs) {
    for_each(sgs.begin(), sgs.end(), print_stickgroup);
}
#endif //ONLINE_JUDGE

int main(void){
    int T; cin >> T;
    
    for (int t = 0; t < T; t++){
        Sticks sticks; 
        int n; cin >> n;
        for (int i = 0; i < n; i++){
            int l_i, w_i; cin >> l_i >> w_i;
            sticks.push_back(Stick(l_i, w_i));
        }

        sort(sticks.begin(), sticks.end());
        Sticks::iterator sticks_end = unique(sticks.begin(), sticks.end());

        // Insert all sticks into stick groups        
        StickGroups sgs;

        //StickGroup fst; fst.largest = Stick(0,0); sgs.push_back(fst);

#ifndef ONLINE_JUDGE
        print_stickgroups(sgs);
#endif //ONLINE_JUDGE

        for(Sticks::iterator it = sticks.begin(); it != sticks_end; ++it){

#ifndef ONLINE_JUDGE
            cerr << " insert: "; print_stick(*it); cerr << endl; //debug
#endif //ONLINE_JUDGE

            // Choose a stick group s.t. min(|it->second - sgs[i].largest|)
            StickGroup s(*it); //s.push_back(*it); s.largest = *it;
            StickGroups::iterator sgi = upper_bound(sgs.begin(), sgs.end(), s, StickGroupComparator());
          
            if (sgi == sgs.begin()){

#ifndef ONLINE_JUDGE
                cerr << "  sgi == sgs.begin()" << endl;
#endif //ONLINE_JUDGE

                sgs.push_back(s);
            }
            else {

#ifndef ONLINE_JUDGE
                cerr << "  sgi != sgs.begin()" << endl;
#endif //ONLINE_JUDGE

                advance(sgi,-1);
                sgi->largest = s.largest;

#ifndef ONLINE_JUDGE
                sgi->push_back(s.back());
#endif //ONLINE_JUDGE

            } 
            sort(sgs.begin(), sgs.end(), StickGroupComparator());
#ifndef ONLINE_JUDGE
            print_stickgroups(sgs);
#endif //ONLINE_JUDGE
        }
        
        cout << sgs.size() << endl;
    }
    return 0;
}
