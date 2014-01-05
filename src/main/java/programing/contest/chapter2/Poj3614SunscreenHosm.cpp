/**
  Sunscreen
  http://poj.org/problem?id=3614   
 */
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct Cow {
    int min_spf;
    int max_spf;
    Cow(int min_spf, int max_spf) : min_spf(min_spf), max_spf(max_spf){}
};
typedef vector<Cow> Cows;

struct Bottle {
    int spf;
    int cover;
    Bottle(int spf, int cover) : spf(spf), cover(cover){}
};
typedef vector<Bottle> Bottles;

struct CowMinSpfComparator {
    bool operator()(const Cow& c1, const Cow& c2){
        return c1.min_spf < c2.min_spf;
    }
};

struct CowMoreMaxSpfComparator {
    bool operator()(const Cow& c1, const Cow& c2){
        return c1.max_spf > c2.max_spf;
    }
};

struct BottleSpfComparator {
    bool operator()(const Bottle& b1, const Bottle& b2){
        return b1.spf < b2.spf;
    }
};

// Prioritize cows with less max_spf  
typedef priority_queue<Cow, vector<Cow>, CowMoreMaxSpfComparator> CowQueue;

// debug
#ifndef ONLINE_JUDGE
static inline void print_cow(const Cow& c) {
    cerr << "c[min_spf: " << c.min_spf << ", max_spf:" << c.max_spf << "], ";
}
static inline void print_cows(const Cows& cs) {
    cerr << "cs{";
    for_each(cs.begin(), cs.end(), print_cow);
    cerr << "}" << endl;
}
static inline void print_bottle(const Bottle& b) {
    cerr << "b[spf: " << b.spf << ", cover:" << b.cover << "], ";
}
static inline void print_bottles(const Bottles& bs) {
    cerr << "bs{";
    for_each(bs.begin(), bs.end(), print_bottle);
    cerr << "}" << endl;
}
static inline void print_cow_queue(CowQueue& cq){
    Cows c;
    cerr << "cq{";
    while(!cq.empty()){
        print_cow(cq.top());
        c.push_back(cq.top());
        cq.pop();
    }
    cerr << "}";
    for(Cows::iterator it = c.begin(); it != c.end(); ++it){
        cq.push(*it);
    }
}
#endif //ONLINE_JUDGE

int main(void){
    // Line 1: Two space-separated integers: C and L
    int C, L; cin >> C >> L;
    
    #ifndef ONLINE_JUDGE
    //cerr << "C:" << C << " L:" << L << endl;
    #endif //ONLINE_JUDGE

    // Lines 2..C+1: Line i describes cow i's lotion requires 
    // with two integers: minSPF_i and maxSPF_i
    Cows cows;
    for(int i = 0; i < C; ++i){
        int min_spf_i, max_spf_i; cin >> min_spf_i >> max_spf_i;
        cows.push_back(Cow(min_spf_i, max_spf_i));
    }

    // Lines C+2..C+L+1: Line i+C+1 describes a sunscreen lotion bottle i 
    // with space-separated integers: SPF_i and cover_i
    Bottles bottles;
    for(int i = 0; i < L; ++i){
        int spf_i, cover_i; cin >> spf_i >> cover_i;
        bottles.push_back(Bottle(spf_i, cover_i));
    }
    sort(cows.begin(), cows.end(), CowMinSpfComparator());
    sort(bottles.begin(), bottles.end(), BottleSpfComparator());

    #ifndef ONLINE_JUDGE
    cerr << endl << "[sorted input]" << endl;
    print_cows(cows);
    print_bottles(bottles);
    #endif //ONLINE_JUDGE

    // number of covered cows
    int n_covered = 0;
    Cows::iterator cit = cows.begin();

    // Prioritize cows with less |cow's max_spf - bottle's spf|  
    CowQueue waiting_cows;

    for(Bottles::iterator bit = bottles.begin(); bit != bottles.end(); ++bit){
        #ifndef ONLINE_JUDGE
        cerr << "[for bottle] "; print_bottle(*bit); cerr << endl;
        #endif //ONLINE_JUDGE

        // Find cows such that cow.min_spf <= bottle.spf <= cow.max_spf
        while(cit != cows.end() && cit->min_spf <= bit->spf){

            #ifndef ONLINE_JUDGE
            cerr << " [cow_queue.push] "; print_cow(*cit); cerr << endl;
            #endif //ONLINE_JUDGE

            waiting_cows.push(*cit);
            advance(cit, 1);
        }
        
        #ifndef ONLINE_JUDGE
        cerr << " [before covering] "; print_cow_queue(waiting_cows); cerr << endl;
        #endif //ONLINE_JUDGE

        int lotion = bit->cover;
        while(lotion > 0 && !waiting_cows.empty()){
            Cow c = waiting_cows.top(); waiting_cows.pop();
            if(c.min_spf <= bit->spf && bit->spf <= c.max_spf){
                // Use this bottle to the cow!
                #ifndef ONLINE_JUDGE
                cerr << " [covered] "; print_cow(c); cerr << lotion << endl;
                #endif //ONLINE_JUDGE

                lotion--;
                n_covered++;
            } 
        }
        
        #ifndef ONLINE_JUDGE
        cerr << " [after covering] "; print_cow_queue(waiting_cows); cerr << endl;
        #endif //ONLINE_JUDGE
    }

    cout << n_covered << endl;

    return 0;
}
