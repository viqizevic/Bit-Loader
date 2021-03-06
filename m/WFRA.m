% Author: Vicky Tanzil (vicky.tanzil@gmail.com)
% Created: 22.11.2014
%
% Function: [En,bn] = WFRA(snrlev,pBudget,gamma)
%
% Cioffi’s rate adaptive Water filling Method
% 
% as described in the script of John M. Cioffi
% (downloadable on his homepage).
%
% Input:
% _ snrlev is a vector containing the snr levels
% _ pBudget is the available power budget
% _ gamma is the SNR gap (Use 1 as default for 0 dB)
%
% Output:
% _ En is the energy in the n-th sub-channel
% _ bn is the bit in the n-th sub-channel
%
function [En,bn] = WFRA(snrlev,pBudget,gamma)

% The maximum number of usable carriers
n = length(snrlev);

% Get sorted values and sorting indices
[ssnrlev,issnrlev] = sort(snrlev, 'descend');

% Initializations
i = n;
K2 = pBudget + sum(gamma./ssnrlev);

negMinEn = true; % if lowest energy is negative

while (negMinEn)
  % Compute WF constant
  K = K2/i;
  % Check lowest energy
  minEn = K - gamma/ssnrlev(i);
  if (minEn <= 0)
    % Update WF constant
    K2 = K2 - gamma/ssnrlev(i);
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

bn = 0.5 * log2( 1 + En.*snrlev);

end