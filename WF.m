% Vicky Tanzil
% vicky.tanzil@gmail.com
% 18.11.2014
%
% Waterfilling’s Method
%
% nlev is a vector containing the noise levels
% pBudget is the available power budget
%
% En is the energy in the n-th sub-channel
%
function En = WF(nlev,pBudget)

% The maximum number of usable carriers
n = length(nlev);

% Get sorted values and sorting indices
[snlev,isnlev] = sort(nlev);

% Initializations
SEn = zeros(n,1); % sorted En
budget = pBudget;

% Start by examining the k-th channel
k = 2;
while (budget > 0 && k <= n)
  % Compute level difference between k-th channel and the channel before
  diff = snlev(k) - ( snlev(k-1) + SEn(k-1) );
  if (budget >= diff*(k-1))
    % There is enough budget, then fill all channel before with the diff value
    SEn(1:k-1) = SEn(1:k-1) + diff;
    budget = budget - diff*(k-1);
  else
    % Otherwise, share with remaining budget to all channels before
    rest = budget/(k-1);
    SEn(1:k-1) = SEn(1:k-1) + rest;
    budget = budget - rest*(k-1);
  end
  % Observe next higher channel
  k = k + 1;
end

if (budget > 0)
  % There is budget left, share this to all channels
  rest = budget/n;
  SEn = SEn + rest;
  budget = budget - rest*n;
end

% sort back
En = zeros(n,1);
En(isnlev) = SEn;

end