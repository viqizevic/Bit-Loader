package model.data;

import java.util.HashMap;
import java.util.Vector;

public class Data {
	
	public static Vector<Integer> getAvaliableModulationRates() {
		Vector<Integer> mods = new Vector<Integer>();
		int n = 11;
		for (int i=1; i <= n; i++) {
			mods.add(i);
		}
		return mods;
	}
	
	public static HashMap<Integer, Double> getSnrOfModulationRateFor1EMinus2Ber() {
		HashMap<Integer, Double> hm = new HashMap<Integer, Double>();
		
		hm.put(0, 0.0);
		hm.put(1, 0.13529736077635854);
		hm.put(2, 2.7059472155271709);
		hm.put(3, 5.4118944310543409);
		hm.put(4, 13.846275095095123);
    	hm.put(5, 24.561319845656509);
    	hm.put(6, 51.913490602253852);
    	hm.put(7, 94.088380640006577);
    	hm.put(8, 188.17908089902);
    	hm.put(9, 349.22763204909018);
    	hm.put(10, 684.50826338639206);
    	hm.put(11, 1291.8007857523794);
    	
		return hm;
	}
	
	public static double[] getSnrPerChannelInDb() {
		double[] snr = {
				18.426404637914111,
				18.422915928228448,
				18.4171053097458,
				18.408978633382944,
				18.398544099218231,
				18.385812265149649,
				18.370796057830272,
				18.353510785731814,
				18.333974154151981,
				18.312206281944924,
				18.288229719716366,
				18.262069469186152,
				18.233753003380063,
				18.203310287271012,
				18.170773798445865,
				18.136178547329223,
				18.099562096449318,
				18.060964578183572,
				18.020428710373846,
				17.977999809152607,
				17.933725798273805,
				17.887657214194771,
				17.839847206110559,
				17.790351530099183,
				17.739228536497709,
				17.686539149594765,
				17.632346838697856,
				17.576717579613522,
				17.51971980556814,
				17.461424346597539,
				17.401904356446785,
				17.34123522604963,
				17.279494482701157,
				17.2167616740998,
				17.153118236516963,
				17.0886473464559,
				17.02343375528724,
				16.957563606497263,
				16.891124235357559,
				16.82420395102006,
				16.756891801259926,
				16.68927732032752,
				16.621450260629025,
				16.553500309228557,
				16.485516790449964,
				16.417588356148919,
				16.349802665519324,
				16.28224605658702,
				16.215003211820335,
				16.148156820545047,
				16.081787241081685,
				16.015972165719678,
				15.950786291796955,
				15.886301002259209,
				15.822584059123601,
				15.759699313262526,
				15.697706433850499,
				15.636660660679389,
				15.576612582344197,
				15.517607943034605,
				15.459687480341383,
				15.402886796105795,
				15.347236261913743,
				15.292760960371552,
				15.239480662809473,
				15.187409843552018,
				15.136557730384334,
				15.086928390341903,
				15.038520849469791,
				14.99132924474797,
				14.945343005970768,
				14.900547065010159,
				14.856922089591361,
				14.81444473846928,
				14.773087934719682,
				14.73282115375007,
				14.693610722591387,
				14.65542012705027,
				14.618210323378616,
				14.581940051246987,
				14.546566144984631,
				14.512043840264568,
				14.478327073659106,
				14.445368772762697,
				14.41312113486612,
				14.381535892462569,
				14.350564564165087,
				14.320158689909482,
				14.290270049602592,
				14.260850864647825,
				14.231853982034421,
				14.203233040911392,
				14.174942621779181,
				14.146938378620852,
				14.119177154459265,
				14.09161708096709,
				14.064217662873491,
				14.036939848005584,
				14.009746083875536,
				13.982600361776909,
				13.955468249388723,
				13.928316912903398,
				13.901115129698562,
				13.873833292562686,
				13.846443406464426,
				13.818919078825015,
				13.79123550421564,
				13.763369444357174,
				13.735299204250653,
				13.707004605213848,
				13.678466955543797,
				13.649669019467581,
				13.620594984985575,
				13.591230431153125,
				13.561562295288319,
				13.531578840536822,
				13.501269624168922,
				13.470625466929862,
				13.439638423712578,
				13.408301755771767,
				13.376609904650509,
				13.344558467945159,
				13.312144176990969,
				13.279364876510126,
				13.246219506225396,
				13.212708084406124,
				13.178831693279612,
				13.144592466208705,
				13.109993576506715,
				13.075039227733086,
				13.039734645287105,
				13.004086069093097,
				12.968100747148316,
				12.931786929684106,
				12.89515386367257,
				12.858211787393632,
				12.820971924762329,
				12.783446479102921,
				12.745648626044463,
				12.707592505203188,
				12.669293210309183,
				12.630766777429542,
				12.592030170937107,
				12.553101266873076,
				12.513998833354147,
				12.474742507679629,
				12.435352769802254,
				12.395850911837856,
				12.356259003304256,
				12.316599851798578,
				12.276896958845418,
				12.237174470675287,
				12.1974571237246,
				12.157770184684535,
				12.11813938496701,
				12.078590849501165,
				12.039151019823709,
				11.999846571480784,
				11.960704325817083,
				11.921751156290352,
				11.883013889514352,
				11.84451920130169,
				11.806293508047997,
				11.768362853869704,
				11.730752793979828,
				11.693488274856316,
				11.656593511826649,
				11.620091864758122,
				11.584005712604659,
				11.548356327616883,
				11.513163750071291,
				11.478446664415179,
				11.444222277755767,
				11.410506201643102,
				11.37731233810667,
				11.344652770903089,
				11.312537662918071,
				11.280975160637654,
				11.249971306563253,
				11.219529960390826,
				11.189652729708556,
				11.1603389108887,
				11.131585440760849,
				11.103386859554371,
				11.075735285491756,
				11.04862040130033,
				11.022029452792419,
				10.995947259542753,
				10.970356237570353,
				10.94523643381188,
				10.920565572056224,
				10.89631910989824,
				10.872470306164757,
				10.84899029816942,
				10.825848188066896,
				10.80301113750167,
				10.78044446968395,
				10.758111777974795,
				10.735975040025689,
				10.713994736494019,
				10.692129973345249,
				10.670338606754717,
				10.648577369636016,
				10.626801998848439,
				10.604967362171649,
				10.583027584180606,
				10.560936170206867,
				10.538646127631793,
				10.516110083822539,
				10.493280400091031,
				10.470109281128423,
				10.446548879441691,
				10.422551394393825,
				10.398069165523633,
				10.373054759894458,
				10.347461053292431,
				10.321241305163426,
				10.294349227243469,
				10.266739045898985,
				10.238365558251051,
				10.209184182211562,
				10.179151000608277,
				10.148222799620941,
				10.116357101791166,
				10.083512193905344,
				10.049647150082395,
				10.014721850427129,
				9.97869699563498,
				9.9415341179565022,
				9.9031955889489645,
				9.8636446244595959,
				9.82284528729947,
				9.7807624880803434,
				9.7373619846980155,
				9.6926103809567525,
				9.6464751248390463,
				9.5989245069347628,
				9.5499276595534148,
				9.4994545570538556,
				9.4474760179359727,
				9.3939637092518637,
				9.3388901539069042,
				9.2822287414366,
				9.2239537428625038,
				9.1640403302501063,
				9.1024646016136845,
				9.0392036118388237,
				8.9742354103210591,
				8.9075390860512691,
				8.8390948209132887,
				8.7688839519985269,
				8.696889043784557,
				8.6230939710716132,
				8.54748401362054,
				8.4700459634899552,
				8.3907682461265747,
				8.3096410563228034,
				8.2266565102166815,
				8.1418088145719025,
				8.0550944546375423,
				7.9665124019475169,
				7.8760643434747992,
				7.7837549336050795,
				7.68959207043205,
				7.593587197900673,
				7.4957556353282317,
				7.3961169358110528,
				7.2946952749684435,
				7.1915198713776105,
				7.0866254399013009,
				6.9800526788941051,
				6.8718487919766407,
				6.7620680446755586,
				6.6507723557221983,
				6.5380319221628378,
				6.423925876638501,
				6.3085429742174917,
				6.191982304984692,
				6.0743540271862209,
				5.9557801140706568,
				5.836395105642687,
				5.7163468543363072,
				5.5957972511206737,
				5.4749229157793,
				5.35391583208197,
				5.2329839053460994,
				5.11235141654211,
				4.992259343747615,
				4.8729655185556293,
				4.7547445821922931,
				4.6378877038501312,
				4.5227020223897219,
				4.40950977244508,
				4.2986470574533255,
				4.1904622355940155,
				4.0853138904235156,
				3.983568366423369,
				3.885596860937377,
				3.7917720780843829,
				3.7024644670222218,
				3.6180380859653347,
				3.5388461538980396,
				3.4652263729545645,
				3.3974961246617714,
				3.3359476611705237,
				3.2808434266511073,
				3.2324116526644211,
				3.1908423732418449,
				3.1562839997011514,
				3.1288405815384661,
				3.1085698583650383,
				3.0954821797844625,
				3.0895403369597334,
				3.0906603135224731,
				3.0987129268604212,
				3.1135262961624663,
				3.1348890431848271,
				3.1625541073806458,
				3.196243040070375,
				3.2356506333062707,
				3.2804497379066904,
				3.3302961311088208,
				3.3848333062543752,
				3.44369707341786,
				3.5065198793353063,
				3.5729347758413361,
				3.6425789868909808,
				3.7150970439872619,
				3.7901434776076162,
				3.8673850674639745,
				3.9465026668639283,
				4.0271926260117672,
				4.1091678459333654,
				4.1921584990823488,
				4.2759124549291938,
				4.3601954493162411,
				4.4447910354605451,
				4.5295003525511168,
				4.6141417452359734,
				4.6985502641962382,
				4.7825770746804164,
				4.8660887964974844,
				4.9489667956737691,
				5.03110644485722,
				5.1124163666695566,
				5.1928176715945789,
				5.272243199667872,
				5.3506367732011375,
				5.4279524660224219,
				5.5041538932273193,
				5.5792135241933716,
				5.6531120205874341,
				5.7258376002705988,
				5.7973854273524061,
				5.8677570281442719,
				5.936959732389095,
				6.0050061388818463,
				6.0719136044265722,
				6.13770375498328,
				6.2024020178303889,
				6.2660371735923128,
				6.32864092704698,
				6.3902474957254025,
				6.4508932154367651,
				6.5106161619899332,
				6.5694557885312754,
				6.62745257807127,
				6.6846477109256508,
				6.7410827469449863,
				6.7967993225469,
				6.8518388626926763,
				6.9062423080634776,
				6.9600498577871157,
				7.0133007281422781,
				7.0660329277229206,
				7.118283049577955,
				7.1700860808520108,
				7.221475230440447,
				7.2724817751360691,
				7.3231349246891311,
				7.3734617061242442,
				7.4234868675629615,
				7.4732328016891678,
				7.5227194888689928,
				7.5719644598016185,
				7.6209827774336727,
				7.6697870377229531,
				7.7183873886881429,
				7.76679156703592,
				7.8150049515155775,
				7.8630306320199628,
				7.9108694933304244,
				7.9585203122958053,
				8.0059798671447044,
				8.0532430575543312,
				8.1003030340434456,
				8.1471513352186662,
				8.1937780313843334,
				8.2401718730262665,
				8.2863204426970167,
				8.3322103088655819,
				8.3778271803449975,
				8.4231560599761934,
				8.4681813963241375,
				8.5128872322304652,
				8.5572573491637129,
				8.6012754064123325,
				8.6449250742742638,
				8.6881901605086149,
				8.7310547294282834,
				8.7735032131250072,
				8.8155205144294158,
				8.8570921013165087,
				8.8982040925705945,
				8.9388433346224971,
				8.97899746956394,
				9.0186549944302374,
				9.0578053119211,
				9.0964387728008962,
				9.1345467102834839,
				9.1721214667628352,
				9.2091564132992261,
				9.2456459623114213,
				9.2815855739588056,
				9.31697175672317,
				9.3518020627194289,
				9.3860750782760132,
				9.4197904103322578,
				9.4529486691991753,
				9.4855514482238661,
				9.5176013008863034,
				9.5491017158400187,
				9.5800570903865143,
				9.6104727028471633,
				9.6403546842658052,
				9.66970998984118,
				9.6985463704510444,
				9.7268723445890988,
				9.7546971709930776,
				9.7820308221970453,
				9.8088839591942047,
				9.8352679073482143,
				9.8611946336423628,
				9.8866767253065024,
				9.9117273698128,
				9.9363603361830766,
				9.9605899575033785,
				9.9844311144962425,
				10.007899219958265,
				10.031010203829995,
				10.053780498629134,
				10.076227024944286,
				10.098367176658517,
				10.120218805548092,
				10.141800204882985,
				10.163130091643373,
				10.1842275869591,
				10.205112194378934,
				10.225803775582316,
				10.246322523159218,
				10.266688930103513,
				10.28692375569177,
				10.307047987453098,
				10.327082798975644,
				10.347049503342252,
				10.36696950204059,
				10.386864229251454,
				10.406755091482825,
				10.426663402585278,
				10.446610314255935,
				10.466616742213084,
				10.48670328829963,
				10.506890158850656,
				10.5271970797371,
				10.547643208572332,
				10.568247044640335,
				10.589026337172015,
				10.609997992658061,
				10.631177981942253,
				10.65258124788606,
				10.674221614433574,
				10.696111697933247,
				10.718262821589907,
				10.740684933924864,
				10.763386532114804,
				10.78637459105958,
				10.809654498996576,
				10.833230000433696,
				10.857103147115993,
				10.881274257672045,
				10.905741886507617,
				10.930502802425631,
				10.955551977355924,
				10.980882585475875,
				11.006486012896538,
				11.032351877979474,
				11.058468062239108,
				11.084820751676443,
				11.111394488283119,
				11.138172231353188,
				11.165135428144046,
				11.192264093339791,
				11.219536896691205,
				11.246931258137121,
				11.274423449653511,
				11.301988703029638,
				11.329601322734899,
				11.357234803016865,
				11.384861948358555,
				11.412454996422658,
				11.439985742620337,
				11.467425665462221,
				11.494746051878582,
				11.521918121732583,
				11.548913150795158,
				11.575702591499962,
				11.602258190852089,
				11.628552104922438,
				11.654557009420961,
				11.68024620590408,
				11.705593723234436,
				11.730574413973319,
				11.755164045446589,
				11.779339385283631,
				11.803078281284414,
				11.826359735521986,
				11.849163972636214,
				11.871472502318737,
				11.893268176028863,
				11.914535238015429,
				11.935259370749842,
				11.955427734901463,
				11.975029004007482,
				11.994053394005629,
				12.012492687810415,
				12.03034025512112,
				12.04759106765346,
				12.064241709986771,
				12.080290386215049,
				12.095736922583137,
				12.110582766279718,
				12.124830980546225,
				12.138486236246173,
				12.151554800022584,
				12.164044519152867,
				12.175964803190837,
				12.187326602465058,
				12.198142383481148,
				12.208426101254666,
				12.218193168579425,
				12.227460422215557,
				12.236246085961573,
				12.244569730555972,
				12.252452230337035,
				12.259915716574433,
				12.2669835273739,
				12.273680154046549,
				12.280031183828068,
				12.286063238830458,
				12.291803911110025,
				12.297281693741084,
				12.302525907795033,
				12.30756662513939,
				12.312434586991623,
				12.317161118187883,
				12.321778037157278,
				12.32631756162812,
				12.330812210133617,
				12.335294699429928,
				12.339797837990275,
				12.344354415792814,
				12.348997090678143,
				12.353758271613076,
				12.35866999925989,
				12.363763824314248,
				12.369070684138709,
				12.374620778281553,
				12.380443443530687,
				12.386567029209605,
				12.393018773473635,
				12.399824681410752,
				12.407009405788843,
				12.414596131321103,
				12.422606463340241,
				12.431060321781251,
				12.439975841369261,
				12.44936927889399,
				12.459254928424055,
				12.469645045274319,
				12.48054977948552,
				12.491977119510295,
				12.503932846722229,
				12.516420501276979,
				12.52944135975696,
				12.542994424926075,
				12.557076427809,
				12.571681842193517,
				12.586802911535045,
				12.602429688123255,
				12.618550084251757,
				12.635149935017001,
				12.652213072262404,
				12.669721409080452,
				12.68765503419106,
				12.705992315429542,
				12.72471001150388,
				12.74378339111918,
				12.763186358517872,
				12.782891584447952,
				12.802870641548182,
				12.823094143128708,
				12.843531884327387,
				12.864152984635954,
				12.884926030814711,
				12.905819219249175,
				12.926800496845761,
				12.947837699614709,
				12.968898688146517,
				12.989951479250914,
				13.010964373094449,
				13.031906075242714,
				13.052745813084016,
				13.073453446183759,
				13.09399957018954,
				13.114355613977278,
				13.134493929796335,
				13.154387876236601,
				13.17401189390209,
				13.193341573733706,
				13.212353717977084,
				13.231026393841512,
				13.249338979940266,
				13.267272205643623,
				13.284808183511627,
				13.301930435005191,
				13.318623909701879,
				13.334874998265406,
				13.3506715394379,
				13.366002821339396,
				13.380859577371552,
				13.39523397703201,
				13.409119611951962,
				13.422511477473533,
				13.435405950084871,
				13.447800761030184,
				13.459694966409179,
				13.471088914076141,
				13.481984207642927,
				13.492383667882601,
				13.502291291822514,
				13.511712209805388,
				13.520652640786997,
				13.52911984612745,
				13.537122082121053,
				13.544668551497294,
				13.551769354111922,
				13.558435437034014,
				13.564678544220607,
				13.570511165956356,
				13.575946488221573,
				13.58099834213737,
				13.585681153622247,
				13.590009893380445,
				13.594000027328137,
				13.597667467549847,
				13.601028523864478,
				13.604099856067277,
				13.606898426902385,
				13.609441455809087,
				13.611746373474571,
				13.613830777216796,
				13.615712387212417,
				13.61740900357788,
				13.618938464305694,
				13.620318604053478,
				13.621567213779922,
				13.622702001220246,
				13.623740552193016,
				13.624700292731282,
				13.625598452033209,
				13.626452026230973,
				13.627277742981409,
				13.628092026887783,
				13.62891096576889,
				13.62975027779928,
				13.630625279552607,
				13.631550854988959,
				13.632541425435713,
				13.633610920620487,
				13.63477275082348,
				13.636039780224641,
				13.63742430152859,
				13.638938011956988,
				13.640591990703193,
				13.642396677948486,
				13.644361855541353,
				13.646496629442378,
				13.648809414036208,
				13.651307918409097,
				13.653999134685634,
				13.656889328511257,
				13.659984031758041,
				13.663288037520481,
				13.66680539745483,
				13.67053942150114,
				13.674492680010793,
				13.67866700828478,
				13.683063513509175,
				13.687682584054688,
				13.692523901086854,
				13.697586452413033,
				13.702868548471766,
				13.708367840350043,
				13.714081339694481,
				13.720005440364174,
				13.726135941655503,
				13.732468072914088,
				13.738996519335034,
				13.745715448741464,
				13.752618539121709,
				13.759699006699094,
				13.76694963430409,
				13.7743627998171,
				13.781930504451728,
				13.789644400652481,
				13.797495819387697,
				13.80547579662835,
				13.813575098815443,
				13.821784247133266,
				13.830093540422927,
				13.838493076589241,
				13.846972772374942,
				13.855522381398412,
				13.864131510374516,
				13.872789633462851,
				13.881486104712518,
				13.890210168598196,
				13.89895096866762,
				13.907697554345766,
				13.916438885965466,
				13.925163838117843,
				13.933861201438276,
				13.942519682964519,
				13.951127905222648,
				13.959674404213809,
				13.968147626489548,
				13.976535925516371,
				13.984827557540045,
				13.993010677167907,
				14.001073332892172,
				14.009003462779383,
				14.016788890550377,
				14.024417322271983,
				14.031876343875464,
				14.039153419708095,
				14.046235892313375,
				14.053110983621828,
				14.059765797718942,
				14.066187325339424,
				14.072362450217836,
				14.078277957405163,
				14.08392054363906,
				14.08927682983313,
				14.094333375726976,
				14.099076696715457,
				14.103493282851565,
				14.107569619994024,
				14.111292213047346,
				14.114647611219887,
				14.117622435203678,
				14.120203406159597,
				14.122377376372125,
				14.124131361420604,
				14.125452573697485,
				14.126328457090215,
				14.126746722630575,
				14.126695384904945,
				14.126162799010196,
				14.12513769783323,
				14.123609229427432,
				14.121566994256366,
				14.119001082074131,
				14.115902108212525,
				14.11226124904767,
				14.108070276423017,
				14.103321590811229,
				14.098008253004759,
				14.092124014133203,
				14.085663343815526,
				14.078621456265768,
				14.070994334182972,
				14.062778750268661,
				14.053972286228795,
				14.04457334913131,
				14.034581185005411,
				14.023995889583841,
				14.012818416105542,
				14.001050580111865,
				13.988695061186283,
				13.975755401604065,
				13.962236001875038,
				13.94814211317958,
				13.933479826714866,
				13.918256059984857,
				13.902478540084608,
				13.886155784045579,
				13.869297076324916,
				13.851912443537495,
				13.834012626544876,
				13.8156090500301,
				13.796713789701638,
				13.777339537283249,
				13.757499563459202,
				13.737207678956334,
				13.716478193955153,
				13.695325876031943,
				13.673765906842448,
				13.651813837765079,
				13.629485544727295,
				13.60679718244343,
				13.583765138295131,
				13.56040598608676,
				13.536736439908033,
				13.512773308333831,
				13.488533449187878,
				13.464033725090996,
				13.439290960008234,
				13.414321896999963,
				13.389143157372061,
				13.363771201408497,
				13.338222290856548,
				13.312512453320508,
				13.286657448704428,
				13.260672737827813,
				13.234573453321426,
				13.20837437289228,
				13.182089895029172,
				13.155734017201544,
				13.129320316586634,
				13.102861933341556,
				13.076371556419666,
				13.049861411913605,
				13.023343253891422,
				12.996828357676764,
				12.970327515510531,
				12.943851034518223,
				12.917408736896052,
				12.891009962218398,
				12.864663571761106,
				12.838377954727367,
				12.81216103625756,
				12.786020287099941,
				12.759962734816208,
				12.733994976393923,
				12.708123192137876,
				12.682353160712301,
				12.656690275207755,
				12.631139560108565,
				12.605705689039539,
				12.580393003174111,
				12.555205530189559,
				12.530147003658952,
				12.505220882773271,
				12.480430372290833,
				12.455778442614889,
				12.431267849903318,
				12.406901156117421,
				12.382680748918972,
				12.358608861326797,
				12.334687591045526,
				12.310918919380036,
				12.287304729649652,
				12.263846825016135,
				12.24054694563938,
				12.217406785074115,
				12.1944280058203,
				12.171612253939404,
				12.148961172648157,
				12.126476414801388,
				12.104159654175636,
				12.082012595466239,
				12.060036982912335,
				12.038234607466547,
				12.016607312429965,
				11.995156997477725,
				11.973885621006273,
				11.952795200741495,
				11.931887812554869,
				11.911165587445748,
				11.890630706659437,
				11.870285394923862,
				11.850131911802448,
				11.830172541176708,
				11.810409578888956,
				11.790845318594243,
				11.771482035889083,
				11.752321970804598,
				11.733367308771673,
				11.714620160185842,
				11.69608253871983,
				11.67775633855112,
				11.659643310690729,
				11.641745038617199,
				11.62406291343585,
				11.606598108797821,
				11.589351555826077,
				11.572323918305205,
				11.555515568399441,
				11.538926563167813,
				11.522556622146457,
				11.506405106266744,
				11.490470998372142,
				11.474752885588337,
				11.45924894378906,
				11.443956924384374,
				11.428874143639575,
				11.413997474710921,
				11.399323342559249,
				11.38484772187569,
				11.370566138123326,
				11.356473671767102,
				11.342564965731086,
				11.328834236087671,
				11.315275285948129,
				11.301881522489079,
				11.288645977013871,
				11.27556132791443,
				11.262619926365659,
				11.24981382455374,
				11.237134806210353,
				11.224574419198721,
				11.212124009873602,
				11.199774758917133,
				11.18751771833568,
				11.175343849289796,
				11.163244060420094,
				11.1512092463267,
				11.139230325858851,
				11.127298279873898,
				11.115404188132054,
				11.10353926500367,
				11.091694893680437,
				11.079862658599813,
				11.068034375813083,
				11.056202121051555,
				11.044358255272638,
				11.03249544749611,
				11.020606694772619,
				11.008685339158802,
				10.996725081607465
		};
		return snr;
	}

}
