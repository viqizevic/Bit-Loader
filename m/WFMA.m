% Author: Vicky Tanzil (vicky.tanzil@gmail.com)
% Created: 22.11.2014
%
% Function: [En,bn] = WFMA(snrlev,totBR,gamma)
%
% Cioffi’s margin adaptive Water filling Method
% 
% as described in the script of John M. Cioffi
% (downloadable on his homepage).
%
% Input:
% _ snrlev is a vector containing the snr levels
% _ totBR is the total bit rate we try to achieve
% _ gamma is the SNR gap (Use 10^(8.8/10) as default for 8.8 dB)
%
% Output:
% _ En is the energy in the n-th sub-channel
% _ bn is the bit in the n-th sub-channel
%
function [En,bn] = WFMA(snrlev,totBR,gamma)

% The maximum number of usable carriers
n = length(snrlev);

% Get sorted values and sorting indices
[ssnrlev,issnrlev] = sort(snrlev, 'descend');

% Initializations
b = totBR;
log2(ssnrlev);
logK2 = 2*b - sum(log2(ssnrlev));

i = n;
negMinEn = true; % if lowest energy is negative

while (negMinEn)
  % Compute WF constant
  logK = log2(gamma) + logK2/i;
  K = 2^(logK);
  % Check lowest energy
  minEn = K - gamma/ssnrlev(i);
  
  if (minEn <= 0)
    % Update WF constant
    logK2 = logK2 + log2(ssnrlev(i));
    i = i - 1;
  else
    negMinEn = false;
  end
end

% Compute WF energies
SEn = max(0, K - gamma./ssnrlev); % sorted En
% sort back
En = zeros(n,1);
En(issnrlev) = SEn;

bn = 0.5 * log2( 1 + En.*snrlev./gamma);

end