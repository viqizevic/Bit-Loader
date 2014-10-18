nlev = [1/19.94 1/17.03 1/17.03 1/10.0 1/10.0 1/2.968 1/2.968 1/0.0552]';
pBudget = 8;
totBR = 8;

[gn,En,bn,margin] = CCB(nlev,pBudget,totBR);


plot(gn);