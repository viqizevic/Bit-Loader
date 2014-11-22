% Vicky Tanzil
% vicky.tanzil@gmail.com
% 18.11.2014
%
% Test call

nlev = [1/19.94 1/17.03 1/17.03 1/10.0 1/10.0 1/2.968 1/2.968 1/0.0552]';
pBudget = 8;
totBR = 8;

disp('Water filling');
En = WF(nlev,pBudget)
lvlWF = En+nlev


snrlev = 1./nlev;
gamma = 1 % 0 dB SNR gap

disp('Water filling (rate adaptive)');
[En, bn] = WFRA(snrlev,pBudget,gamma)


disp('Hughes-Hartoggs');
[En, bn] = HH(nlev,pBudget,totBR)

