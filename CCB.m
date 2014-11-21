% Author: Vicky Tanzil (vicky.tanzil@gmail.com)
% Created: 04.11.2014
%
% Function: [gn,En,bn,margin] = CCB(nlev,pBudget,totBR)
%
% Chow Cioffi Bingham’s Method
% as described in the lecture of Dr. Volker Jungnickel at TU Berlin
% based on the paper
% A Practical Discrete Multitone Transceiver Loading Algorithm
% for Data Transmission over Spectrally Shaped Channels
% by Peter S. Chow, John M. Cioffi, and John A.C. Bingham
% IEEE Transactions on communications, Vol. 43, 1995
%
% Input:
% _ nlev is a vector containing the noise levels
% _ pBudget is the available power budget
% _ totBR is the total bit rate we try to achieve
%
% Output:
% _ gn is channel gain
% _ En is the energy in the n-th sub-channel (PAM or QAM)
% _ bn is the bit in the n-th sub-channel
% _ margin is the margin used at the end
%
function [gn,En,bn,margin] = CCB(nlev,pBudget,totBR)

% The maximum number of usable carriers
n = length(nlev);

% Distribute power equally over all subcarriers and compute SNR values based on this power
snr = (pBudget/n) ./ nlev;

% The SNR gap in dB as the well known gap approximation
gap = 9.8;

% Set current system performance margin gammaMargin as 0 dB
margin = 0;

% The desired number of bits per DMT symbol
bitsTarget = totBR;

% Initialization
iterateCount = 0;
maxCount = 10;
bitsTotal = 0;
b = zeros(n,1);
bn = zeros(n,1);
diff = zeros(n,1);

while (bitsTotal < bitsTarget && iterateCount < maxCount)
  
  b = log2( 1 + snr/(10^((gap+margin)/10)) );
  bn = floor(b);
  diff = b - bn;
  usedCarriers = n - length(find(bn == 0));

  % Stop and declare bad channel if bitsTotal = 0
  bitsTotal = sum(bn);
  if (bitsTotal == 0)
    error('Bad channel..');
  end

  % Compute new gammaMargin
  margin = margin + 10 * log10( 2^((bitsTotal-bitsTarget)/usedCarriers) );
  
  iterateCount = iterateCount + 1;
end

while (bitsTotal > bitsTarget)
  % Subtract one bit at a time from carrier with smallest diff(i)
  Indices = find(bn>0);
  [value,idx] = min(diff(Indices));
  idx = Indices(idx);
  bn(idx) = bn(idx) - 1;
  diff(idx) = b(idx) - bn(idx);
  bitsTotal = bitsTotal - 1;
end

while (bitsTotal < bitsTarget)
  % Add one bit at a time on the carrier that has the largest diff(i)
  [value,idx] = max(diff);
  bn(idx) = bn(idx) + 1;
  diff(idx) = b(idx) - bn(idx);
  bitsTotal = bitsTotal + 1;
end

% Calculate gain and energy
gn = ( 2.^(2*bn) - 1 ) * 10^((gap+margin)/10);
En = gn .* nlev;

end