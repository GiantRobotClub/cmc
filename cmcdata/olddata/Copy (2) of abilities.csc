0#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
10#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
11#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
12#NCODE;#NCODE;#NCODE;#START;CMP>=^15^map:slf:sm:none:none:1:^;A-SET^slf^sm^none^none^15^;END;#NCODE;#NCODE;#NCODE;#START;A-SET^slf^sm^none^none^map:slf:sm:none:none:0.75:^;L-SET^slf^sm^none^none^mlp:slf:sm:none:none:0.5:^;MAKMS^12^t^map:slf:sm:none:none:1:^mlp:slf:sm:none:none:1:^;END;#
13#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
14#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^10^;END;#
15#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^all^bm^none^none^10^;END;#
16#NCODE;#NCODE;#NCODE;#START;AMODF^abs^sm^slime^none^3^;LMODF^abs^sm^slime^none^3^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
17#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COUNT^all^bm^slime^none^;AMODF^trg^bm^slime^none^cnt:5^;END;#
18#START;COUNT^all^bm^slime^none^;A-SET^slf^sm^none^none^cnt:20:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
19#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
20#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTG^2^;REQ;MAKED^38^t;END;#
21#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
22#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COUNT^all^sm^dark elf^none^;MANAG^ownr^cnt:1:^;END;#
23#NCODE;#NCODE;#NCODE;#START;DRAWC^ownr^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
24#NCODE;#NCODE;#NCODE;#START;COUNT^all^sm^dark elf^Kelar Warrior^;LMODF^slf^sm^none^none^cnt:6:^;AMODF^slf^sm^none^none^cnt:6:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
25#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;NODIZ^trg^bm^none^none;DIZZY^trg^bm^none^none^;END;#
26#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^25^;END;#
27#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTG^1^;DISCA^targ^;REQ;DRAWC^ownr^;LIFEP^targ^-10^;END;#
28#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;AMODF^trg^bm^dark elf^none^8^;LMODF^trg^bm^dark elf^none^8^;END;#
29#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
30#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTG^4^;REQ;MANAD^ownr^3^;MANAL^ownr^3^;DEND;#
31#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;RHEAL^slf^sm^none^none^;HEALD^slf^sm^none^none^;END;#
32#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTG^3;REQ;L-SET^slf^sm^none^none^mlp:slf:sm:none:none:0.5:^;DAMAG^abs^bm^none^none^10^;DIZZY^abs^bm^none^none^;END;#
33#NCODE;#START;COSTG^4^;REQ;RETRN;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^30^;END;#
34#START;DRAWC^ownr^;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
35#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
36#NCODE;#NCODE;#NCODE;#START;MANAG^ownr^1;END;#NCODE;#NCODE;#NCODE;#NCODE;#
37#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;MAKMD^10^t;END;#
38#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^atk^om^none^none^5^;END;#NCODE;#
39#NCODE;#NCODE;#NCODE;#DEFER;UPK;COSTG^2^;REQ;HEALD^all^sm^dark elf^none^;DEND;#NCODE;#NCODE;#NCODE;#COSTG^6^;REQ;START;MAKMD^21^t;END;#
40#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;RHEAL^trg^bm^slime^none^;HEALD^trg^bm^slime^none^;END;#
41#START;LIFEP^targ^-50^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
42#START;AMODF^trg^bm^none^none^25^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
43#START;DIZZY^trg^bm^none^none;DAMAG^trg^bm^none^none^30^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
44#START;DAMAG^trg^bm^none^none^10^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
45#START;MANAG^targ^5^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
46#START;DESTR^trg^be^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
47#START;LIFEP^targ^mad:targ:-3:^;LIFEP^targ^mag:targ:-3:^;LIFEP^targ^mal:targ:-3:^;SMANL^targ^0^;SMAND^targ^0^;SMANG^targ^0^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
48#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;MANAG^ownr^2^;END;#
49#START;GRAVE^e;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
50#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
51#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^40^;DAMAG^slf^bm^none^none^40;END;#
52#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
53#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;MAKES^72^t^map:slf:sm:none:none:1:^mlp:slf:sm:none:none:1:^;DESTR^slf^sm^none^none^;END;#
54#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
55#NCODE;#START;LIFEP^ownr^-15;RETRN;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
56#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
57#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^30^;DAMAG^slf^sm^none^none^7^#
58#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
59#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;MANAD^ownr^3;DAMAG^slf^sm^none^none^10^;END;#
60#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DRAWC^ownr^;DISCA^ownr^;END;#
61#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
62#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
63#NCODE;#NCODE;#START;AMODF^abs^sm^follower^none^5^;LMODF^abs^sm^follower^none^5^;END;#START;AMODF^abs^sm^follower^none^5^;LMODF^abs^sm^follower^none^5^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
64#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^1^;REQ;AMODF^slf^sm^none^none^25;LMODF^slf^sm^none^none^25;LIFEP^ownr^-50^;DEND;#
65#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;AMODF^trg^bm^none^none^map:slf:sm:none:none:1:^;LMODF^trg^bm^none^none^mlp:slf:sm:none:none:1:^;DESTR^slf^sm^none^none^;#
66#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DRAWC^ownr^;LIFEP^ownr^-35^;DEND;#
67#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^40^;LIFEP^ownr^-50^;DEND;#
68#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;POSSE;DEND;#
69#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^8^;REQ;DESTR^trg^bm^none^none^;END;#
70#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
71#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
72#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^atk^om^none^none^8^;END;#START;MAKMS^53^t^map:slf:se:none:none:1:^mlp:slf:se:none:none:1:^;DESTR^slf^se^none^none^;END;#
73#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^6^;REQ;DESTR^all^bm^none^none^;DESTR^slf^bb^none^none^;END;#
74#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^1^;REQ;MAKMD^50^t;END;#
75#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
76#NCODE;#NCODE;#NCODE;#START;MANAD^ownr^1;END;#NCODE;#NCODE;#NCODE;#NCODE;#
77#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;MANAD^ownr^2^;LIFEP^ownr^-30^;END;#
78#START;LIFEP^ownr^map:trg:bm:none:none:1:^;LIFEP^ownr^mlp:trg:bm:none:none:1:^;DESTR^trg^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
79#START;DESTR^trg^be^none^none;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
80#START;MANAD^targ^5^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
81#START;L-SET^trg^bm^none^none^mlp:trg:bm:none:none:1.75:^;A-SET^trg^bm^none^none^map:trg:bm:none:none:1.75:^;LIFEP^ownr^-50^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
82#START;MAKMD^71^t^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
83#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;AMODF^trg^sm^none^none^0^;AMODF^slf^sm^none^none^map:trg:sm:slime:Gloop:1:^;AMODF^slf^bm^none^none^mlp:trg:sm:slime:Gloop:1:^;LMODF^slf^sm^none^none^map:trg:sm:slime:Gloop:1:^;LMODF^slf^sm^none^none^mlp:trg:sm:slime:Gloop:1:^;DESTR^trg^sm^slime^Gloop^;DEND;#
84#START;A-SET^slf^bm^none^none^dek:ownr:1:^;L-SET^slf^bm^none^none^dek:ownr:1:^;END;#NCODE;#NCODE;#START;A-SET^slf^bm^none^none^dek:ownr:1:^;L-SET^slf^bm^none^none^dek:ownr:1:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
85#START;A-SET^slf^bm^none^none^hnd:ownr:10:^;L-SET^slf^bm^none^none^hnd:ownr:10:^;END;#NCODE;#NCODE;#START;A-SET^slf^bm^none^none^hnd:ownr:10:^;L-SET^slf^bm^none^none^hnd:ownr:10:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
86#START;A-SET^slf^bm^none^none^tdk:ownr:2:^;L-SET^slf^bm^none^none^tdk:ownr:2:^;AMODF^slf^bm^none^none^dek:ownr:-2:^;LMODF^slf^bm^none^none^dek:ownr:-2:^;END;#NCODE;#NCODE;#START;A-SET^slf^bm^none^none^tdk:ownr:2:^;L-SET^slf^bm^none^none^tdk:ownr:2:^;AMODF^slf^bm^none^none^dek:ownr:-2:^;LMODF^slf^bm^none^none^dek:ownr:-2:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
87#NCODE;#NCODE;#NCODE;#START;COUNT^abs^sm^none^none^;LMODF^slf^sm^none^none^cnt:3:^;AMODF^slf^sm^none^none^cnt:3:^;DAMAG^abs^sm^none^none^10^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
88#NCODE;#NCODE;#NCODE;#NCODE;;#NCODE;#NCODE;#NCODE;#START;COUNT^all^sm^none^none^;LMODF^slf^sm^none^none^cnt:1:^;COUNT^all^om^none^none^;AMODF^slf^sm^none^none^cnt:1:^;END;#
89#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
90#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^15^;DIZZY^slf^bm^none^none^;DAMAG^trg^bm^none^none^15^;END;#
91#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#TCMB*;AMODF^trg^bm^bird^none^0;AMODF^all^bm^bird^none^5^;LMODF^all^bm^bird^none^5^;END;#NCODE;#NCODE;#
92#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^sm^none^none^20^;DRAWC^ownr^;DEND;#
93#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
94#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
95#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSC;atkA^slf^sm^none^none^0^0^;#NCODE;#NCODE;#
96#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
97#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;MAKMD^98^t;END;#
98#NCODE;#NCODE;#NCODE;#START;CMP>=^rnd:1:100:^80^;MANAG^ownr^1;MANAL^ownr^1^;MANAD^ownr^1;DESTR^slf^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
101#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
102#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;RHEAL^trg^bm^none^none^;HEALD^trg^bm^none^none^;END;#
103#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^1;REQ;ISDIZ^trg^bm^none^none;UNDIZ^trg^bm^none^none^;END;#
104#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^1;REQ;NODIZ^trg^bm^none^none;DIZZY^trg^bm^none^none^;END;#
105#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^15^;END;#
106#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;LIFEP^targ^-10^;END;#
107#NCODE;#NCODE;#START;NODIZ^slf^sm^none^none^;LMODF^slf^sm^none^none^10^;AMODF^slf^sm^none^none^10^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
108#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;LMODF^trg^bm^cleric^none^35^;AMODF^trg^bm^cleric^none^-10^;END;#
109#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
110#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^8^;REQ;A-SET^trg^bm^none^none^0^;END;#
111#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;LIFEP^both^20;END;#
112#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;MERGE^Green Sprite^114^map:slf:sm:none:none:2:^mlp:trg:sm:none:none:2^;END;#
113#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;MERGE^Red Sprite^114^map:trg:sm:none:none:2:^mlp:slf:sm:none:none:2^;END;#
114#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;MANAL^ownr^5^;END;#
115#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;RHEAL^all^sm^fairy^none^;HEALD^all^sm^fairy^none^;END;#
116#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COUNT^all^sm^fairy^none^;LMODF^all^bm^fairy^none^cnt:5:^;END;#
117#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
118#NCODE;#NCODE;#NCODE;#START;MANAL^ownr^2^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
119#NCODE;#START;COSTL^3^;REQ;RETRN;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
120#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
121#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
123#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
124#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^4^;REQ;HEALD^slf^sm^none^none^;LIFEP^targ^20^;END;#
125#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^2;REQ;HEALD^all^bm^none^none^;LIFEP^both^20^;END;#
126#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^4;REQ;MAKMD^102^t;DEND;#
127#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DRAWC^targ^;END;#
128#NCODE;#NCODE;#NCODE;#START;MANAL^ownr^1;END;#NCODE;#NCODE;#NCODE;#NCODE;#
129#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;POSSE;DEND;#
130#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
131#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^10^;REQ;CALLM^142^;END;#
132#NCODE;#NCODE;#NCODE;#DEFER;UPK;COSTL^1;REQ;END;#START;CMP>=^map:atk:bm:none:none:1:^60^;DIZZY^atk^bm^none^none^;END;#NCODE;#START;CMP>=^map:atk:bm:none:none:1:^60^;DIZZY^atk^bm^none^none^;END;#NCODE;#
133#NCODE;#NCODE;#NCODE;#DEFER;UPK;COSTL^2;REQ;RHEAL^all^sm^fairy^none^;HEALD^all^sm^fairy^none^;END;#NCODE;#NCODE;#START;COUNT^atk^om^none^none^;MANAL^ownr^cnt:2:^;END;#NCODE;#
134#NCODE;#NCODE;#NCODE;#DEFER;UPK;COSTL^1;REQ;#NCODE;#NCODE;#START;COUNT^atk^om^none^none^;DRAWN^ownr^cnt:1:^;END;#NCODE;#
135#NCODE;#NCODE;#NCODE;#DEFER;UPK;COSTL^1;REQ;#START;COUNT^atk^sm^none^none^;DRAWN^ownr^cnt:1:^;END;#NCODE;#NCODE;#NCODE;#
136#START;LIFEP^targ^50^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
137#START;DRAWC^targ^;DRAWC^targ^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
138#START;MANAL^targ^5^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
139#START;DAMAG^trg^bm^none^none^map:trg:bm:none:none:1^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
140#START;A-SET^trg^bm^none^none^map:trg:bm:none:none:0.5^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
142#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
143#NCODE;#START;RETRN;END;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
144#NCODE;#NCODE;#NCODE;#START;COUNT^all^sm^fairy^none;A-SET^slf^sm^none^none^cnt:20:^;L-SET^slf^sm^none^none^cnt:20:^;#NCODE;#NCODE;#NCODE;#NCODE;#
145#START;A-SET^trg^bm^none^none^map:trg:bm:none:none:2^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
148#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^1^;REQ;MAKMD^109^t;END;#
150#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
151#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
152#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
153#START;DRAWC^ownr^;DRAWC^ownr^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
154#NCODE;#NCODE;#NCODE;#DEFER;CNISM;END;#NCODE;#NCODE;#NCODE;#NCODE;#
155#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;AMODF^slf^sm^none^none^mlp:trg:sm:none:none:1:^;DESTR^trg^sm^none^none^;DEND;#
156#NCODE;#NCODE;#NCODE;#DEFER;DIZZY^slf^sm^none^none^;COSTG^5^;COSTD^5^;COSTL^5^;REQ;UNDIZ^slf^sm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
157#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^1^;REQ;DRAWC^ownr^;DISCA^ownr^;DEND;#
158#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DRAWC^both^;END;#
159#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;KILL^opnt^a^;END;#
160#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;NODIZ^trg^bm^none^none;DIZZY^trg^be^none^none^;END;#
161#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^7^;REQ;RETTA;END;#
162#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^6^;REQ;DESTR^all^be^none^none^;END;#
170#NCODE;#START;RETRN;END;#NCODE;#START;DESTR^slf^be^none^none^;DEND;#NCODE;#NCODE;#NCODE;#START;COSTL^1^;COSTG^1^;COSTD^1^;REQ;MAKMD^150^t;DEND;#
171#NCODE;#NCODE;#NCODE;#START;MANAG^ownr^1;MANAD^ownr^1;MANAL^ownr^1;END;#NCODE;#NCODE;#NCODE;#NCODE;#
172#NCODE;#NCODE;#NCODE;#START;MANAL^ownr^2;END;#NCODE;#NCODE;#NCODE;#NCODE;#
173#NCODE;#NCODE;#NCODE;#START;MANAG^ownr^2;END;#NCODE;#NCODE;#NCODE;#NCODE;#
174#NCODE;#NCODE;#NCODE;#START;MANAD^ownr^2;END;#NCODE;#NCODE;#NCODE;#NCODE;#
175#START;GRAVE^m;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
176#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;AMODF^trg^bm^none^none^mag:ownr:3:^;AMODF^trg^bm^none^none^mad:ownr:3:^;AMODF^trg^bm^none^none^mal:ownr:3:^;SMANL^ownr^0^;SMAND^ownr^0^;SMANG^ownr^0^;DEND;#
177#START;A-SET^trg^bm^none^none^rnd:1:100:^;L-SET^trg^bm^none^none^rnd:1:100:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
178#START;DESTR^trg^bb^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
179#START;DISCA^targ^;LIFEP^targ^-35^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
180#NCODE;#START;RETRN;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
181#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;LIFEP^targ^map:all:sm:slime:none:-0.5:^;LIFEP^targ^mlp:all:sm:slime:none:-0.5:^;DESTR^all^sm^slime^none^;END;#
182#NCODE;#NCODE;#NCODE;#START;MANAG^ownr^1;MANAD^ownr^1;MANAL^ownr^1;END;#NCODE;#NCODE;#NCODE;#NCODE;#
183#NCODE;#NCODE;#NCODE;#START;A-SET^slf^sm^none^none^30^;END;#NCODE;#NCODE;#NCODE;#AMODF^slf^sm^none^none^10^;DAMAG^slf^sm^none^none^10^;DEND;#
184#START;DRAWC^targ^;DRAWC^targ^;DRAWC^targ^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
185#NCODE;#NCODE;#NCODE;#START;MANAD^ownr^mad:opnt:0.3333:^;MANAL^ownr^mal:opnt:0.3333:^;MANAG^ownr^mag:opnt:0.3333:^;#NCODE;#NCODE;#NCODE;#NCODE;#
190#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^3^;COSTG^3^;COSTD^3^;REQ;LIFEP^targ^30^;DEND;#
191#START;LISET^ownr^300^;END;#START;KILL^ownr^a^;END;#NCODE;#START;LIFEP^ownr^-40^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
192#NCODE;#NCODE;#NCODE;#DEFER;UPK;COSTG^1^;COSTD^1^;REQ;MANAL^ownr^2;END;#NCODE;#NCODE;#NCODE;#NCODE;#
193#NCODE;#NCODE;#NCODE;#DEFER;UPK;COSTL^1^;COSTD^1^;REQ;MANAG^ownr^2;END;#NCODE;#NCODE;#NCODE;#NCODE;#
194#NCODE;#NCODE;#NCODE;#DEFER;UPK;COSTG^1^;COSTL^1^;REQ;MANAD^ownr^2;END;#NCODE;#NCODE;#NCODE;#NCODE;#
195#START;SMANL^both^10^;SMANG^both^10^;SMAND^both^10^;MANAD^ownr^6;MANAL^ownr^6;MANAG^ownr^6;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
196#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^6^;COSTG^6^;COSTL^6^;REQ;DESTR^trg^bm^none^none^;DEND;#
197#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^3^;COSTG^3^;COSTL^3^;REQ;DRAWC^ownr^;DEND;#
198#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;NODIZ^trg^sm^none^none;DIZZY^trg^sm^none^none^;MANAL^ownr^1;MANAG^ownr^1^;MANAD^ownr^1^;DEND;#
201#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
202#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;CMP>=^rnd:1:100:^90^;DIZZY^atk^bm^none^none^;END;#NCODE;#
203#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^5^;REQ;GRAVE^m;END;#
204#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^20^;END;#
205#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^3^;REQ;MAKMD^rnd:376:383:^t^;END;#
206#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
207#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^5^;END;#
208#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;AMODF^trg^bm^none^none^10^;END;#
209#START;DRAWC^ownr^;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
210#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
211#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;NODIZ^trg^bm^mage^none;DIZZY^trg^bm^mage^none^;END;#
212#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;RETRN;END;#
213#START;LIFEP^ownr^-50^;END;#NCODE;#NCODE;#START;LIFEP^ownr^-40^;END#NCODE;#NCODE;#NCODE;#NCODE;#
214#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
215#NCODE;#NCODE;#NCODE;#START;A-SET^slf^sm^none^none^mlp:slf:sm:none:none:1:^;END#NCODE;#NCODE;#NCODE;#NCODE;#
216#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
217#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DRAWC^targ^;END;#
218#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^3^;REQ;MAKMD^rnd:384:386:^t^;END;#
219#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;RHEAL^trg^bm^none^none^;HEALD^trg^bm^none^none^;END;#
220#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^20^;END;#
221#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;RHEAL^trg^bm^robot^none^;HEALD^trg^bm^robot^none^;END;#
222#NCODE;#NCODE;#NCODE;#START;CRDON^Piddo^;HEALD^slf^sm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
223#NCODE;#NCODE;#NCODE;#START;CRDON^Podder-Head^;HEALD^slf^sm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
224#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
225#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
226#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;RHEAL^trg^bm^none^none^;HEALD^trg^bm^none^none^;END;#
227#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
228#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^2^;RHEAL^trg^bm^none^none^;REQ;HEALD^trg^bm^none^none^;DEND;#
229#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^1^;REQ;MAKMD^227^t;END;#
230#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;AMODF^trg^bm^none^none^rnd:-20:20:^;LMODF^trg^bm^none^none^rnd:-10:20:^;END;#
231#NCODE;#NCODE;#NCODE;#START;MANAL^ownr^1;END;#NCODE;#NCODE;#NCODE;#NCODE;#
232#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^8^;REQ;REVRT^trg^bm^none^none^;END;#
233#NCODE;#NCODE;#NCODE;#DEFER;UPK;COSTL^1;DAMAG^all^bm^none^none^10^;REQ;END;#NCODE;#NCODE;#NCODE;#NCODE;#
234#START;NODIZ^trg^bb^none^none^;DIZZY^trg^bb^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
235#START;DAMAG^trg^bm^none^col[2^40^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
236#START;LMODF^trg^bm^none^none^15^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
237#START;AMODF^trg^bm^none^none^15^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
238#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;LIFEP^ownr^75^;DESTR^slf^be^none^none^;END;#
239#START;ISDIZ^trg^bm^none^none;UNDIZ^trg^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
240#START;AMODF^trg^sm^none^none^0^;RETTA;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
241#START;DISCA^ownr^;DRAWC^ownr^;DRAWC^ownr^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
242#START;LMODF^trg^bm^none^none^15^;AMODF^trg^bm^none^none^15^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
243#START;DRAWN^targ^rnd:0:4:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
244#START;DESTR^trg^bm^none^col[2^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
245#START;DESTR^trg^be^none^col[8^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
251#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^6^;COSTL^1^;COSTG^1^;REQ;MAKMS^371^s^map:slf:bm:none:none:2:^mlp:slf:bm:none:none:2:^;DEND;#
252#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;NODIZ^trg^bm^none^none^;DIZZY^trg^bm^none^none^;LIFEP^ownr^-30;END;#
253#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^5^;COSTL^1^;COSTG^1^;REQ;MAKMS^372^s^map:slf:bm:none:none:2:^mlp:slf:bm:none:none:2:^;DEND;#
254#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^4^;COSTL^1^;COSTG^1^;REQ;MAKMS^373^s^map:slf:bm:none:none:2:^mlp:slf:bm:none:none:2:^;DEND;#
255#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^4^;COSTL^1^;COSTG^1^;REQ;MAKMS^374^s^map:slf:bm:none:none:2:^mlp:slf:bm:none:none:2:^;DEND;#
256#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
257#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
258#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
259#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
260#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^mlp:slf:bm:none:none:1:^;DESTR^slf^sm^none^none^;END;#
261#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
262#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DESTR^trg^bm^none^none^;LIFEP^ownr^-100^;END;#
263#NCODE;#START;RETRN;END;#START;CRDON^Meck^;UNDIZ^slf^sm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
264#NCODE;#START;RETRN;END;#START;CRDON^Kemmit^;UNDIZ^slf^sm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
265#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^1^;COSTG^1^;COSTD^1^;NODIZ^trg^bm^robot^none;REQ;DIZZY^trg^bm^robot^none^;DEND;#
266#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
267#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
268#START;DRAWC^ownr^;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
269#NCODE;#NCODE;#NCODE;#START;DAMAG^slf^bm^none^none^10^;AMODF^slf^bm^none^none^-5^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
270#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
271#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#COSTD^7^;REQ;REMOV^trg^bm^none^none^;END;#
272#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
273#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
274#NCODE;#NCODE;#START;NODIZ^slf^bm^none^none^;LIFEP^ownr^-15^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
275#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
276#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^5^;REQ;MAKMD^375^t^;END;#
277#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
278#START;DESTR^trg^be^none^none^;LIFEP^ownr^-50^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
279#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^3^;REQ;L-SET^slf^bm^none^none^mlp:trg:bm:none:none:1:^;A-SET^slf^bm^none^none^map:trg:bm:none:none:1:^;DEND;#
280#NCODE;#NCODE;#NCODE;#DEFER;UPK;COSTD^2^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
281#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
282#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
283#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DRAWC^targ^;END;#
284#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^4^;REQ;DAMAG^trg^bm^none^none^20^;DEND;#
285#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
286#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;ISDIZ^trg^bm^none^none;UNDIZ^trg^bm^none^none^;DAMAG^trg^bm^none^none^15^;END;#
287#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
288#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^3;REQ;MAKMD^256^t;DEND;#
289#NCODE;#NCODE;#NCODE;#START;MANAD^ownr^1;END;#NCODE;#NCODE;#NCODE;#NCODE;#
290#START;DAMAG^trg^bm^none^none^30^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
291#START;LIFEP^targ^map:all:bm:none:none:-0.25:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
292#START;REMOV^trg^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
293#START;AMODF^trg^sm^none^none^0^;TEMPV^map:trg:sm:none:none:0.5:^;AMODF^all^sm^none^none^cnt:1:^;DESTR^trg^sm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
294#START;ISDIZ^trg^bm^none^none^;UNDIZ^trg^bm^none^none^;DAMAG^trg^bm^none^none^10^;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
295#START;MAKMD^389^t^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
301#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
302#NCODE;#NCODE;#START;RETRN;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
303#NCODE;#NCODE;#NCODE;#START;CMP>=^rnd:1:100:^75^;DESTR^slf^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
304#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
305#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
306#START;DRAWC^ownr^;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
307#START;DRAWC^ownr^;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
308#START;DRAWC^ownr^;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
309#START;DRAWC^ownr^;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
310#NCODE;#NCODE;#NCODE;#DEFER;CRDON^Plum^;DIZZY^slf^bm^none^none^;DRAWC^ownr^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
311#NCODE;#NCODE;#START;CRDON^Number 347^;MANAG^ownr^1^;END;#DEFER;CRDON^Number 347^;DIZZY^slf^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
312#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
313#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
314#NCODE;#START;COSTG^1^;REQ;RETRN;END;#NCODE;#START;CRDON^Larry^;HEALD^slf^sm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
315#NCODE;#START;COSTG^1^;REQ;RETRN;END;#NCODE;#START;CRDON^Earl^;HEALD^slf^sm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
316#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;CRDON^Tonya^;CRDON^Exodragon^;DRAWC^targ^;END;#
317#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;CRDON^Hawk^;CRDON^Exodragon^;ISDIZ^trg^bm^none^none^;UNDIZ^trg^bm^none^none^;END;#
318#NCODE;#NCODE;#NCODE;#START;CRDON^Tonya^;CRDON^Hawk^;LMODF^slf^bm^none^none^3^;AMODF^slf^bm^none^none^3^;#NCODE;#NCODE;#NCODE;#NCODE;#
319#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
320#NCODE;#START;COSTG^2^;REQ;RETRN^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
321#NCODE;#NCODE;#START;CRDON^Shadehawk^;DRAWC^ownr^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
322#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
323#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTG^1;REQ;NODIZ^trg^bm^none^none;DIZZY^trg^bm^none^none^;END;#
324#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
325#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
326#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;LMODF^slf^sm^none^none^mlp:trg:sm:none:none:0.5:^;DESTR^trg^sm^none^none^;END;#
327#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
328#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;AMODF^trg^bm^none^none^10^;DAMAG^trg^bm^none^none^5^;#
329#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DISCA^ownr^;DIZZY^trg^bm^none^none^;DAMAG^trg^bm^none^none^10^;DEND;#
330#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;NODIZ^trg^sm^none^none^;DIZZY^trg^sm^none^none^;MANAG^ownr^1^;DEND;#
331#NCODE;#NCODE;#NCODE;#DEFER;CMP>=^rnd:1:100:^80^;DIZZY^slf^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
332#NCODE;#NCODE;#NCODE;#START;MANAG^ownr^rnd:0:2:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
333#NCODE;#NCODE;#NCODE;#START;#NCODE;#NCODE;#NCODE;#START;COSTG^2^;REQ;MAKMD^312^t;END;#
334#NCODE;#NCODE;#NCODE;#START;MANAG^ownr^1^;#NCODE;#NCODE;#NCODE;#NCODE;#
335#NCODE;#NCODE;#START;DISCA^ownr^;DRAWC^ownr^;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
336#NCODE;#NCODE;#NCODE;#DEFER^;UPK;COSTG^3^;REQ;DRAWC^ownr^;HEALD^all^bm^none^col[8^;AMODF^all^bm^none^col[8^3^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
337#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DIZZY^rnd[5^bm^none^none^;UNDIZ^rnd[5^bm^none^none^;END;#
338#START;DAMAG^trg^bm^none^none^10^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
339#START;AMODF^trg^bm^none^none^-25^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
340#START;HANDL^targ^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
341#START;DESTR^trg^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
342#START;MANAL^ownr^rnd:1:4:^;MANAD^ownr^rnd:1:4:^;MANAG^ownr^rnd:2:8:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
343#START;DESTR^trg^bb^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
344#START;DRAWN^targ^2^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
345#START;NODIZ^trg^bm^none^none^;DIZZY^trg^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
346#START;ISDIZ^trg^bm^none^none^;UNDIZ^trg^bm^none^none^;AMODF^trg^bm^none^none^-5^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
347#START;AMODF^trg^bm^none^none^-10^;LMODF^trg^bm^none^none^10^;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
348#START;DESTR^trg^sm^none^none^;DRAWN^ownr^2^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
351#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
352#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^none^50^;END;#
353#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;HEALD^trg^bm^none^none^;LMODF^trg^bm^none^none^20^;END;#
354#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
355#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
356#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DRAWC^targ^;END;#
357#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
358#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
359#NCODE;#NCODE;#NCODE;#START;MANAL^ownr^1;MANAD^ownr^1;END;#NCODE;#NCODE;#NCODE;#START;MAKED^387^s^;DEND;#
360#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;NODIZ^trg^bm^none^none^;DIZZY^trg^bm^none^none^;END;#
361#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTD^1^;REQ;DAMAG^trg^bm^none^none^lif:ownr:0.2:^;END;#
362#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;COSTL^1^;REQ;DAMAG^trg^bm^none^none^lif:opon:0.2:^;END;#
363#START;A-SET^trg^bm^none^none^0^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
364#START;LIFEP^targ^50^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
365#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^abs^bm^none^none^map:slf:bm:none:none:0.25:^;END;#
371#NCODE;#START;MAKMD^251^s^;NOTOK;END;#NCODE;#START;MAKMS^251^s^map:slf:bm:none:none:0.5:^mlp:slf:bm:none:none:0.5:^;#NCODE;#NCODE;#NCODE;#START;DESTR^trg^bm^none^none^;END;#
372#NCODE;#START;MAKMD^253^s^;NOTOK;END;#NCODE;#START;MAKMS^253^s^map:slf:bm:none:none:0.5:^mlp:slf:bm:none:none:0.5:^;#NCODE;#NCODE;#NCODE;#START;NODIZ^trg^bm^none^none^;DIZZY^trg^bm^none^none^;END;#
373#NCODE;#START;MAKMD^254^s^;NOTOK;END;#NCODE;#START;MAKMS^254^s^map:slf:bm:none:none:0.5:^mlp:slf:bm:none:none:0.5:^;#NCODE;#NCODE;#NCODE;#NCODE;#
374#NCODE;#START;MAKMD^255^s^;NOTOK;END;#NCODE;#START;MAKMS^255^s^map:slf:bm:none:none:0.5:^mlp:slf:bm:none:none:0.5:^;#NCODE;#NCODE;#NCODE;#NCODE;#
375#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DIZZY^slf^bm^none^none^;CMP>=^rnd:1:100:^50^;POSSE;END;#
376#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
377#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
378#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
379#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
380#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
381#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#START;DAMAG^all^om^none^none^10^;END;#NCODE;#NCODE;#NCODE;#
382#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
383#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#START;COSTL^1^;REQ;RHEAL^trg^bm^none^none^;HEALD^trg^bm^none^none^;DEND;#
384#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
385#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
386#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
387#NCODE;#START;MAKED^359^s^;NOTOK;END;#NCODE;#START;MANAL^ownr^1;MANAG^ownr^1;END;#NCODE;#NCODE;#NCODE;#START;MAKED^388^s^;DEND;#
388#NCODE;#START;MAKED^359^s^;NOTOK;END;#NCODE;#START;MANAG^ownr^1;MANAD^ownr^1;END;#NCODE;#NCODE;#NCODE;#START;MAKED^359^s^;DEND;#
389#NCODE;#NCODE;#NCODE;#START;DESTR^slf^bm^none^none^;DEND;#NCODE;#NCODE;#NCODE;#NCODE;#
401#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
402#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^atk^om^none^none^10^;END;#NCODE;#
403#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSC;gsaA^all^bb^none^none^1.50^0^;dsaA^all^bb^none^none^1.50^0^;lsaA^all^bb^none^ntok^1.50^0^;#NCODE;#NCODE;#
404#NCODE;#NCODE;#NCODE;#START;MANAL^ownr^1^;MANAG^ownr^1^;MANAD^ownr^1^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
405#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSC;gcoA^all^bb^none^none^1^1^;dcoA^all^bb^none^none^1^1^;lcoA^all^bb^none^none^1^1^;#NCODE;#NCODE;#
406#NCODE;#NCODE;#NCODE;#START;MANAL^ownr^-1^;MANAG^ownr^-1^;MANAD^ownr^-1^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
407#NCODE;#NCODE;#START;DRAWC^ownr^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
408#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSP;ovrA^both^0^0^;#NCODE;#NCODE;#
409#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSC;atkC^all^bm^none^none^0.5^0^;#NCODE;#NCODE;#
410#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSS;PNOSP^both^;#NCODE;#NCODE;#
411#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSS;PNOEF^both^;#NCODE;#NCODE;#
412#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSC;atkD^all^bm^none^none^1^10^;#NCODE;#NCODE;#
413#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSS;M2ndL^all^bm^none^none^0^0^;#NCODE;#NCODE;#
414#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#TPBL;DISCA^targ^;END;#NCODE;#NCODE;#
415#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#TCMBZ;LIFEP^both^-20^;END;#NCODE;#NCODE;#
416#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#TCMS*;OIDIZ^trg^bb^none^none^;DRAWC^opnt^;END;#NCODE;#NCODE;#
417#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#TCMBK;MANAL^both^1^;MANAG^both^1^;MANAD^both^1^;END;#NCODE;#NCODE;#
418#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSC;dcoA^all^bb^none^none^1^-1^;#NCODE;#NCODE;#
419#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSC;lcoA^all^bb^none^none^1^-1^;#NCODE;#NCODE;#
420#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSC;gcoA^all^bb^none^none^1^-1^;#NCODE;#NCODE;#
421#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
422#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;RHEAL^trg^bm^none^none^;HEALD^trg^bm^none^none^;END;#
423#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSC;atkA^all^sm^paladin^none^1^5^;#NCODE;#NCODE;#
424#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
425#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#TCMBA;AMODF^trg^bm^none^none^-20^;END;#NCODE;#NCODE;#
426#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
427#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
428#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;DAMAG^trg^bm^none^col[2^20^;END;#
429#NCODE;#START;RETRN;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
430#NCODE;#START;DAMAG^all^bm^paladin^none^20^;AMODF^all^bm^paladin^none^15^;END;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;ISDIZ^trg^bm^none^none^;UNDIZ^trg^bm^none^none^;END;#
431#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#CONSP;ovrA^ownr^0.5^0^;#NCODE;#NCODE;#
432#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
433#NCODE;#NCODE;#NCODE;#START;AMODF^abs^bm^none^none^15^;DIZZY^abs^bm^none^none^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
444#NCODE;#NCODE;#NCODE;#START;COUNT^abs^be^none^Column of Iridith^;MANAL^ownr^cnt:1:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
445#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#START;GRAVE^m;DESTR^slf^bm^none^none^;END;#
450#NCODE;#NCODE;#NCODE;#START;COUNT^all^sm^paladin^none^;MANAL^ownr^cnt:1:^;END;#NCODE;#NCODE;#NCODE;#NCODE;#
451#START;UNDIZ^trg^bm^none^none^;AMODT^trg^bm^none^none^30^;LMODT^trg^bm^none^none^40^;END#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#NCODE;#
