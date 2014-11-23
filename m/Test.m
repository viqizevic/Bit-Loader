% Author: Vicky Tanzil (vicky.tanzil@gmail.com)
% Created: 22.11.2014
%
% Call example


nlev = [1/19.94 1/17.03 1/17.03 1/10.0 1/10.0 1/2.968 1/2.968 1/0.0552]';
pBudget = 8;
totBR = 8;


disp('Water filling by Jungnickel');
En = WF(nlev,pBudget)
lvlWF = En+nlev


snrlev = 1./nlev;
gamma = 1 % 0 dB SNR gap

disp('Water filling (rate adaptive) by Cioffi');
[En, bn] = WFRA(snrlev,pBudget,gamma)
lvlWF = En+nlev


gamma = 10^(8.8/10); % 8.8 dB SNR gap

disp('Water filling (margin adaptive) by Cioffi');
[En, bn] = WFMA(snrlev,totBR,gamma)


disp('Hughes-Hartoggs');
[En, bn] = HH(nlev,pBudget,totBR)


disp('Chow Cioffi Bingham');
[gn,En,bn,margin] = CCB(nlev,pBudget,totBR)