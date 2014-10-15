noiseLevels = [1/19.94 1/17.03 1/17.03 1/10.0 1/10.0 1/2.968 1/2.968 1/0.0552];
powerBudget = 8;
totalBitRate = 8;

n = length(noiseLevels);
gammaInDb = 9.8;
gammaMarginInDb = 0;

snr = zeros(n,1);
for i=1:n
  power = powerBudget/n;
  snr(i) = power / noiseLevels(i);
end;

iterateCount = 0;
maxCount = 10;

usedCarriers = n;
bitsTotal = 0;

bitsTarget = totalBitRate;

b = zeros(n,1);
b2 = zeros(n,1);
diff = zeros(n,1);

while (bitsTotal < bitsTarget && iterateCount < maxCount)

  usedCarriers = n;

  for i=1:n
    b(i) = log2( 1 + snr(i) / ( 10 ^ ( (gammaInDb + gammaMarginInDb) / 10 ) ) );
    b2(i) = floor(b(i));
    diff(i) = b(i) - b2(i);
    if (b2(i) == 0)
      usedCarriers = usedCarriers - 1;
    end
  end

  bitsTotal = sum(b2);
  if (bitsTotal == 0)
    error('Bad channel..');
  end

  gammaMarginInDb = gammaMarginInDb + 10 * log10( 2 ^ ((bitsTotal-bitsTarget)/usedCarriers) );

  iterateCount = iterateCount + 1;
end


