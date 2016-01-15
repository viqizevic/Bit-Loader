% Author: Vicky Tanzil (vicky.tanzil@gmail.com)
% Created: 21.11.2014
%
% Function: [En, bn] = HH(nlev,pBudget,totBR)
%
% Hughes-Hartoggs’s Method
% as described in the lecture of Dr. Volker Jungnickel at TU Berlin
%
% Input:
% _ nlev is a vector containing the noise levels
% _ pBudget is the available power budget
% _ totBR is the total bit rate we try to achieve
%
% Output:
% _ En is the energy in the n-th sub-channel
% _ bn is the bit in the n-th sub-channel
%
function [En, bn] = HH(nlev,pBudget,totBR)

% The maximum number of usable carriers
n = length(nlev);

% Initializations
En = zeros(n,1);
budget = pBudget;
target = totBR;
bn = zeros(n,1); % transferred bits in each channel
transfer = 0; % total transferred bits

while (transfer < target)
  % get the channel with minimal required power
  % if one more bit added
  snr = 10.^(LTSItable(bn + 1)/10);
  reqEn = nlev .* snr;
  [newEn,idx] = min(reqEn);
  
  % update needed power budget
  oldEn = En(idx);
  update = budget - newEn + oldEn;
  if (update < 0)
    break
  end
  budget = update;

  % update bits transferred
  bn(idx) = bn(idx) + 1;
  transfer = transfer + 1;
  En(idx) = newEn;
end

end


% Link to system interface table
%  #bits   SNR (dB)
%    1       6
%    2       9
%    3      12
%    4      15
% To simplify this we use this function
% SNR = 3*(#bits) + 3
%
function snr = LTSItable(bits)
  snr = 3*bits + 3;
end