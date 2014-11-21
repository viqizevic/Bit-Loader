
% The maximum number of usable carriers
n = length(nlev);

function snr = LTSItable(bits)
  snr = 3*bits + 3;
end


% Initializations
En = zeros(n,1);
budget = pBudget;
target = 8;
tb = zeros(n,1); % transferred bits in each channel
transfer = 0; % total transferred bits

while (transfer < target)
  % get the channel with minimal required power
  % if one more bit added
  snr = 10.^(LTSItable(tb + 1)/10);
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
  tb(idx) = tb(idx) + 1;
  transfer = transfer + 1;
  En(idx) = newEn;
end

usedBudget = pBudget - budget;







