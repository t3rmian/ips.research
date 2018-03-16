package io.github.t3r1jj.ips.collector.model.algorithm

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.t3r1jj.ips.collector.model.data.InertialDataset
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PedometerTest {
    companion object {
        const val TEST_DATA_JSON = "{\"movementType\":\"STAIRS_UP\",\"acceleration\":[{\"data\":[-1.2737153,2.0925324,10.400413],\"timestamp\":22844641617000},{\"data\":[-0.8619126,1.9057846,10.0269165],\"timestamp\":22844661599000},{\"data\":[-0.45968673,2.384625,9.691729],\"timestamp\":22844681552000},{\"data\":[-0.6703765,2.9113493,9.442732],\"timestamp\":22844701555000},{\"data\":[-0.6536171,2.1452048,8.571242],\"timestamp\":22844721752000},{\"data\":[-0.60812724,1.2737153,9.088389],\"timestamp\":22844741555000},{\"data\":[-0.8714894,0.79487497,10.1226845],\"timestamp\":22844761545000},{\"data\":[-0.8834604,0.842759,10.110714],\"timestamp\":22844781565000},{\"data\":[-0.8834604,1.9919758,9.528923],\"timestamp\":22844801529000},{\"data\":[-0.4309563,1.532289,9.14585],\"timestamp\":22844822914000},{\"data\":[-0.4309563,1.532289,8.887277],\"timestamp\":22844841437000},{\"data\":[-0.3878607,1.685518,10.036493],\"timestamp\":22844861784000},{\"data\":[-0.3184288,2.2984335,10.333375],\"timestamp\":22844881842000},{\"data\":[0.047884032,1.8770541,9.931149],\"timestamp\":22844901807000},{\"data\":[-0.29688102,1.9440918,7.9104424],\"timestamp\":22844921792000},{\"data\":[-0.34955344,2.2697031,9.414001],\"timestamp\":22844943177000},{\"data\":[0.1340753,2.5187001,11.061212],\"timestamp\":22844961502000},{\"data\":[0.1340753,2.0973208,10.869676],\"timestamp\":22844981495000},{\"data\":[-0.45010993,1.8579005,8.561666],\"timestamp\":22845002394000},{\"data\":[-0.63206923,2.0590134,8.829816],\"timestamp\":22845021772000},{\"data\":[-0.22026655,1.733402,10.362105],\"timestamp\":22845041550000},{\"data\":[0.21068975,1.6807296,10.831368],\"timestamp\":22845063469000},{\"data\":[-0.22984336,2.0398598,8.647857],\"timestamp\":22845081745000},{\"data\":[-0.61291564,2.4995465,8.197746],\"timestamp\":22845101934000},{\"data\":[-0.37349546,2.231396,9.998186],\"timestamp\":22845124614000},{\"data\":[0.23942018,2.2744915,10.668563],\"timestamp\":22845141544000},{\"data\":[0.2825158,1.9057846,9.308656],\"timestamp\":22845161502000},{\"data\":[-0.38307226,2.1260512,8.829816],\"timestamp\":22845183187000},{\"data\":[-0.22984336,2.3175871,10.189722],\"timestamp\":22845201484000},{\"data\":[-0.18674773,2.2744915,9.969456],\"timestamp\":22845222765000},{\"data\":[-0.23223756,2.2098482,8.552089],\"timestamp\":22845244374000},{\"data\":[-0.2777274,2.2098482,9.136273],\"timestamp\":22845261544000},{\"data\":[0.0,1.9057846,10.400413],\"timestamp\":22845281527000},{\"data\":[-0.22984336,2.1260512,9.481039],\"timestamp\":22845303189000},{\"data\":[-0.4405331,2.1164744,8.935161],\"timestamp\":22845321982000},{\"data\":[-0.7086837,2.0685902,9.203311],\"timestamp\":22845341809000},{\"data\":[-0.30645782,2.0015526,10.477027],\"timestamp\":22845361947000},{\"data\":[-0.1340753,2.2026656,9.825804],\"timestamp\":22845381924000},{\"data\":[-0.1340753,2.3558946,8.935161],\"timestamp\":22845401525000},{\"data\":[-0.5650316,2.2840683,8.973468],\"timestamp\":22845421655000},{\"data\":[-0.29688102,2.2337902,10.266336],\"timestamp\":22845441502000},{\"data\":[-0.29688102,2.1691468,9.615114],\"timestamp\":22845461590000},{\"data\":[-0.009576807,1.9728222,9.184157],\"timestamp\":22845482530000},{\"data\":[-0.20111294,1.9895816,9.385271],\"timestamp\":22845501607000},{\"data\":[-0.272939,2.2218192,9.959879],\"timestamp\":22845521737000},{\"data\":[-0.5746084,2.5761611,9.385271],\"timestamp\":22845542495000},{\"data\":[-0.50278234,2.2888567,9.040505],\"timestamp\":22845561502000},{\"data\":[-0.49081135,2.0494366,9.107543],\"timestamp\":22845581840000},{\"data\":[-0.4405331,2.066196,10.055647],\"timestamp\":22845601942000},{\"data\":[-0.37349546,2.566584,9.816227],\"timestamp\":22845622770000},{\"data\":[-0.37349546,2.504335,9.193734],\"timestamp\":22845641544000},{\"data\":[-0.4213795,1.8579005,9.002198],\"timestamp\":22845661590000},{\"data\":[-0.90021986,2.1930888,9.069236],\"timestamp\":22845681504000},{\"data\":[-0.89064306,2.2409728,9.605537],\"timestamp\":22845701475000},{\"data\":[-0.6895301,1.934515,9.165004],\"timestamp\":22845724290000},{\"data\":[-0.38307226,1.5705963,9.346964],\"timestamp\":22845741792000},{\"data\":[-0.02873042,2.1260512,9.873688],\"timestamp\":22845761519000},{\"data\":[0.4213795,3.0262709,9.885659],\"timestamp\":22845782284000},{\"data\":[0.10534488,3.0119057,8.8777],\"timestamp\":22845801464000},{\"data\":[-0.36391866,2.9951463,8.860941],\"timestamp\":22845823004000},{\"data\":[-0.4309563,2.6910827,8.920795],\"timestamp\":22845841972000},{\"data\":[-0.4309563,2.336741,8.6095495],\"timestamp\":22845861889000},{\"data\":[-0.02873042,1.7908629,9.548077],\"timestamp\":22845881847000},{\"data\":[0.31603462,2.4229321,9.701305],\"timestamp\":22845904399000},{\"data\":[0.30645782,3.0454245,10.055647],\"timestamp\":22845921687000},{\"data\":[0.06703765,3.3135753,10.477027],\"timestamp\":22845941574000},{\"data\":[-0.17238252,3.074155,10.460267],\"timestamp\":22845962277000},{\"data\":[-0.12449849,2.7006595,10.395624],\"timestamp\":22845981547000},{\"data\":[-0.2873042,2.3654714,9.548077],\"timestamp\":22846001522000},{\"data\":[-0.35434186,2.72939,9.203311],\"timestamp\":22846023377000},{\"data\":[-0.545878,3.4859576,9.203311],\"timestamp\":22846041492000},{\"data\":[-0.7086837,3.6583402,8.408437],\"timestamp\":22846061792000},{\"data\":[-1.0630256,3.3997664,8.00621],\"timestamp\":22846081995000},{\"data\":[-1.0486604,2.719813,9.097966],\"timestamp\":22846101914000},{\"data\":[-0.23942018,2.3750482,10.036493],\"timestamp\":22846121619000},{\"data\":[-0.08619126,2.087744,9.3661175],\"timestamp\":22846141587000},{\"data\":[-0.047884032,2.4420857,8.896853],\"timestamp\":22846161497000},{\"data\":[-0.110133275,2.930503,8.1881695],\"timestamp\":22846181474000},{\"data\":[-0.110133275,3.2273839,7.9583263],\"timestamp\":22846203830000},{\"data\":[0.22026655,2.9975405,8.743625],\"timestamp\":22846222782000},{\"data\":[0.2873042,2.7389667,8.360553],\"timestamp\":22846241854000},{\"data\":[-0.22984336,3.2848446,8.207323],\"timestamp\":22846264335000},{\"data\":[-0.2681506,3.4380736,9.385271],\"timestamp\":22846281512000},{\"data\":[0.06703765,2.2697031,10.496181],\"timestamp\":22846301549000},{\"data\":[-0.18195933,2.4708161,8.63828],\"timestamp\":22846323272000},{\"data\":[-0.7182605,3.169923,8.628703],\"timestamp\":22846341515000},{\"data\":[-0.78529817,3.169923,9.586384],\"timestamp\":22846361814000},{\"data\":[0.50757074,2.4899697,11.166556],\"timestamp\":22846382045000},{\"data\":[1.1971009,3.5434184,11.415554],\"timestamp\":22846401840000},{\"data\":[-0.047884032,4.8841715,8.714894],\"timestamp\":22846421640000},{\"data\":[-0.89064306,4.841076,10.821792],\"timestamp\":22846441572000},{\"data\":[-0.6895301,4.4915223,14.470555],\"timestamp\":22846461629000},{\"data\":[0.5554548,3.9360676,17.152061],\"timestamp\":22846481474000},{\"data\":[-0.16280572,2.432509,14.030022],\"timestamp\":22846503917000},{\"data\":[-1.6663644,2.1260512,12.440272],\"timestamp\":22846522710000},{\"data\":[-1.4748282,1.13964,14.489709],\"timestamp\":22846541945000},{\"data\":[-1.2258313,-0.6799533,14.81532],\"timestamp\":22846564279000},{\"data\":[-1.532289,-1.5227122,13.053187],\"timestamp\":22846581539000},{\"data\":[-2.0111294,-0.9959879,10.7356],\"timestamp\":22846601519000},{\"data\":[-2.6623523,0.48841715,7.383718],\"timestamp\":22846623125000},{\"data\":[-1.8195933,0.7278373,7.824251],\"timestamp\":22846641452000},{\"data\":[-1.436521,0.9576807,9.107543],\"timestamp\":22846661895000},{\"data\":[-0.89064306,0.9576807,10.208876],\"timestamp\":22846684294000},{\"data\":[-0.6512229,0.5267244,10.196905],\"timestamp\":22846701580000},{\"data\":[-0.6512229,0.5267244,8.743625],\"timestamp\":22846721575000},{\"data\":[-0.842759,1.982399,8.025364],\"timestamp\":22846741544000},{\"data\":[-0.545878,2.0207062,7.8050976],\"timestamp\":22846761447000},{\"data\":[-0.58897364,1.982399,6.14831],\"timestamp\":22846781819000},{\"data\":[-0.58897364,1.4269443,4.855441],\"timestamp\":22846801909000},{\"data\":[-0.56982,1.091756,5.363012],\"timestamp\":22846822760000},{\"data\":[-0.11492168,0.7469909,6.7708025],\"timestamp\":22846841522000},{\"data\":[-0.545878,0.9768343,7.4507556],\"timestamp\":22846863050000},{\"data\":[-0.545878,0.92895025,7.201759],\"timestamp\":22846881525000},{\"data\":[-0.7278373,1.8195933,6.8665705],\"timestamp\":22846901507000},{\"data\":[-0.30645782,2.432509,8.0924015],\"timestamp\":22846924114000},{\"data\":[-0.25378537,2.7389667,9.720459],\"timestamp\":22846941844000},{\"data\":[-0.4309563,1.8195933,10.955867],\"timestamp\":22846961844000},{\"data\":[-1.1683705,1.0342951,11.21444],\"timestamp\":22846984379000},{\"data\":[-1.3215994,1.0342951,11.252748],\"timestamp\":22847001512000},{\"data\":[-0.7565677,1.5227122,11.252748],\"timestamp\":22847021630000},{\"data\":[0.019153614,3.2752678,13.283031],\"timestamp\":22847043030000},{\"data\":[-0.58418524,4.817134,13.43626],\"timestamp\":22847061474000},{\"data\":[-1.5131354,3.9073372,13.388376],\"timestamp\":22847081740000},{\"data\":[-1.1492168,0.19153613,14.5567465],\"timestamp\":22847103992000},{\"data\":[0.24899697,-4.405331,13.40753],\"timestamp\":22847121660000},{\"data\":[0.7469909,-4.2042184,10.429143],\"timestamp\":22847141550000},{\"data\":[0.7469909,0.8619126,9.500193],\"timestamp\":22847163022000},{\"data\":[0.6895301,4.9895163,9.911995],\"timestamp\":22847181484000},{\"data\":[0.34476504,4.975151,8.465898],\"timestamp\":22847201764000},{\"data\":[-0.2777274,2.9113493,9.959879],\"timestamp\":22847222909000},{\"data\":[-0.90979666,0.60333884,10.70687],\"timestamp\":22847241835000},{\"data\":[-0.91937345,-0.5746084,11.300632],\"timestamp\":22847261839000},{\"data\":[-0.16280572,2.9783869,12.660539],\"timestamp\":22847284292000},{\"data\":[0.08619126,2.72939,10.907983],\"timestamp\":22847301640000},{\"data\":[-0.6607997,1.3599066,5.8705826],\"timestamp\":22847321622000},{\"data\":[-1.5897499,1.3599066,2.6527755],\"timestamp\":22847343137000},{\"data\":[-1.436521,1.4077905,3.3518825],\"timestamp\":22847361519000},{\"data\":[-0.79487497,1.0247183,3.9839516],\"timestamp\":22847381489000},{\"data\":[-0.58418524,-0.22984336,3.3614593],\"timestamp\":22847403299000},{\"data\":[-0.1340753,-0.21787235,4.1659107],\"timestamp\":22847422237000},{\"data\":[0.18195933,1.5801731,5.2768207],\"timestamp\":22847441710000},{\"data\":[0.51714754,2.882619,5.6215854],\"timestamp\":22847462025000},{\"data\":[0.51714754,2.633622,8.90643],\"timestamp\":22847481814000},{\"data\":[-0.5554548,2.2984335,11.616667],\"timestamp\":22847501554000},{\"data\":[-1.1587936,1.2545617,12.440272],\"timestamp\":22847521652000},{\"data\":[-1.3503298,0.61291564,11.243171],\"timestamp\":22847541502000},{\"data\":[-0.9576807,2.6240451,13.273455],\"timestamp\":22847561460000},{\"data\":[0.19153613,5.2863975,16.64449],\"timestamp\":22847583917000},{\"data\":[-0.22026655,5.3007627,17.286137],\"timestamp\":22847601849000},{\"data\":[-0.9672575,2.3750482,16.606182],\"timestamp\":22847622770000},{\"data\":[-2.6719291,-1.1971009,14.231135],\"timestamp\":22847644289000},{\"data\":[-2.9592333,-3.1603463,13.656527],\"timestamp\":22847661537000},{\"data\":[-3.8115692,-2.9592333,11.990162],\"timestamp\":22847681515000},{\"data\":[-3.4955344,-1.3503298,10.668563],\"timestamp\":22847703060000},{\"data\":[-3.3135753,-0.10534488,7.680599],\"timestamp\":22847721484000},{\"data\":[-2.5187001,0.91937345,7.1155677],\"timestamp\":22847741834000},{\"data\":[-1.8100165,2.3750482,8.350976],\"timestamp\":22847761945000},{\"data\":[-0.06703765,2.3918076,11.779472],\"timestamp\":22847781895000},{\"data\":[0.545878,1.5514427,12.200851],\"timestamp\":22847801792000},{\"data\":[-0.06703765,1.3120226,9.442732],\"timestamp\":22847824562000},{\"data\":[-0.7661445,2.1164744,7.4124484],\"timestamp\":22847841579000},{\"data\":[-0.7781156,1.5993267,7.8146744],\"timestamp\":22847861540000},{\"data\":[-0.7613561,0.80445176,7.3166804],\"timestamp\":22847882305000},{\"data\":[-0.7613561,-0.06703765,5.257667],\"timestamp\":22847901542000},{\"data\":[-1.0726024,-0.39264908,3.2369606],\"timestamp\":22847921479000},{\"data\":[-1.4748282,0.8810662,3.4093432],\"timestamp\":22847943914000},{\"data\":[-0.93852705,1.8866309,5.2385135],\"timestamp\":22847961762000},{\"data\":[-0.19153613,0.9864111,6.205771],\"timestamp\":22847981819000},{\"data\":[-0.17717093,0.7374141,7.2687964],\"timestamp\":22848001987000},{\"data\":[-0.93852705,2.078167,8.0445175],\"timestamp\":22848022754000},{\"data\":[-0.641646,2.6144683,9.911995],\"timestamp\":22848041519000},{\"data\":[-1.0821792,2.3080103,10.390836],\"timestamp\":22848061587000},{\"data\":[-1.4556746,1.2066777,11.204864],\"timestamp\":22848081485000},{\"data\":[-1.9249382,1.4173675,13.321339],\"timestamp\":22848101487000},{\"data\":[-0.7374141,3.074155,16.749836],\"timestamp\":22848124684000},{\"data\":[-0.5363012,3.0214825,16.242264],\"timestamp\":22848141900000},{\"data\":[-1.532289,1.0534488,12.871228],\"timestamp\":22848161859000},{\"data\":[-1.7046716,1.0414777,14.81532],\"timestamp\":22848184394000},{\"data\":[-0.58418524,-2.1930888,15.600618],\"timestamp\":22848201520000},{\"data\":[0.7565677,-4.357447,13.924677],\"timestamp\":22848221625000},{\"data\":[1.3982137,-1.8483237,9.385271],\"timestamp\":22848243122000},{\"data\":[0.2777274,1.9249382,5.6790466],\"timestamp\":22848261537000},{\"data\":[0.260968,5.3725886,8.580819],\"timestamp\":22848281552000},{\"data\":[0.7565677,3.3614593,9.816227],\"timestamp\":22848302734000},{\"data\":[0.59376204,2.0973208,10.180145],\"timestamp\":22848321935000},{\"data\":[-0.6703765,2.135628,8.054094],\"timestamp\":22848341544000},{\"data\":[-1.5035586,2.834735,8.12592],\"timestamp\":22848363035000},{\"data\":[-1.2258313,2.2409728,9.921572],\"timestamp\":22848381485000},{\"data\":[-1.2258313,1.3311762,9.921572],\"timestamp\":22848401824000},{\"data\":[-1.7046716,0.58418524,7.4603324],\"timestamp\":22848422917000},{\"data\":[-2.7006595,0.521936,4.5298295],\"timestamp\":22848441839000},{\"data\":[-2.5187001,0.7278373,4.4915223],\"timestamp\":22848461549000},{\"data\":[-1.6184803,-0.1436521,4.477157],\"timestamp\":22848481567000},{\"data\":[-0.6703765,0.019153614,3.8786068],\"timestamp\":22848501499000},{\"data\":[0.2777274,0.8619126,4.4819455],\"timestamp\":22848521834000},{\"data\":[0.076614454,2.1164744,4.960786],\"timestamp\":22848543895000},{\"data\":[0.076614454,2.930503,6.129156],\"timestamp\":22848561890000},{\"data\":[0.093373865,2.9137435,8.887277],\"timestamp\":22848581550000},{\"data\":[-0.23942018,2.4037786,12.430696],\"timestamp\":22848602272000},{\"data\":[-0.81402856,0.9864111,13.560759],\"timestamp\":22848621609000},{\"data\":[-2.0398598,0.9768343,12.421119],\"timestamp\":22848641472000},{\"data\":[-1.5035586,2.5187001,13.579912],\"timestamp\":22848663567000},{\"data\":[-0.02873042,4.654328,16.97968],\"timestamp\":22848681650000},{\"data\":[0.6991069,4.582502,17.477673],\"timestamp\":22848701920000},{\"data\":[-0.7661445,2.2026656,14.441825],\"timestamp\":22848724500000},{\"data\":[-1.6567876,0.9864111,14.968549],\"timestamp\":22848741507000},{\"data\":[-2.9879637,-0.9768343,13.934254],\"timestamp\":22848761500000},{\"data\":[-4.1946416,-3.1124623,11.291056],\"timestamp\":22848783162000},{\"data\":[-4.759673,-2.566584,8.868123],\"timestamp\":22848801584000},{\"data\":[-4.0318356,-0.7661445,7.795521],\"timestamp\":22848821642000},{\"data\":[-3.1124623,-0.41180268,6.799533],\"timestamp\":22848842390000},{\"data\":[-2.4995465,0.79487497,8.130709],\"timestamp\":22848861625000},{\"data\":[-1.8483237,1.1971009,9.576807],\"timestamp\":22848881535000},{\"data\":[-1.5610195,0.90021986,10.25676],\"timestamp\":22848902325000},{\"data\":[-1.1492168,0.60333884,9.212888],\"timestamp\":22848921484000},{\"data\":[-0.8714894,0.5602432,7.9966335],\"timestamp\":22848941792000},{\"data\":[-0.12449849,0.89064306,8.973468],\"timestamp\":22848963895000},{\"data\":[-0.08619126,1.4748282,7.6614456],\"timestamp\":22848981597000},{\"data\":[0.22026655,0.90021986,5.9184666],\"timestamp\":22849001519000},{\"data\":[-0.19153613,0.4213795,4.3287168],\"timestamp\":22849023527000},{\"data\":[0.019153614,0.8619126,5.813122],\"timestamp\":22849043567000},{\"data\":[0.45010993,1.4556746,6.8282633],\"timestamp\":22849061882000},{\"data\":[-0.22984336,2.6048915,5.7748146],\"timestamp\":22849084309000},{\"data\":[-0.60333884,2.6048915,6.483498],\"timestamp\":22849101522000},{\"data\":[-0.94810385,1.7046716,7.6710224],\"timestamp\":22849121570000},{\"data\":[-0.7661445,1.3215994,10.553641],\"timestamp\":22849143124000},{\"data\":[-0.80924016,1.3215994,12.5264635],\"timestamp\":22849161550000},{\"data\":[-1.1683705,1.8004397,12.325351],\"timestamp\":22849181547000},{\"data\":[-1.8866309,2.9017725,11.683704],\"timestamp\":22849201529000},{\"data\":[-1.3503298,3.6774938,15.198392],\"timestamp\":22849221602000},{\"data\":[-0.19153613,1.3694834,16.85518],\"timestamp\":22849241549000},{\"data\":[-0.1340753,-0.6607997,13.8959465],\"timestamp\":22849261580000},{\"data\":[-0.641646,-1.4939818,11.262324],\"timestamp\":22849281560000},{\"data\":[-0.20111294,-1.5466543,12.08593],\"timestamp\":22849301540000},{\"data\":[0.39264908,-1.388637,11.722012],\"timestamp\":22849322417000},{\"data\":[0.23942018,-0.22984336,10.400413],\"timestamp\":22849341525000},{\"data\":[-0.11492168,1.13964,8.66701],\"timestamp\":22849361455000},{\"data\":[-0.40222588,1.9249382,9.452309],\"timestamp\":22849383929000},{\"data\":[-0.45968673,2.078167,10.94629],\"timestamp\":22849401715000},{\"data\":[-0.2873042,2.432509,10.247183],\"timestamp\":22849422775000},{\"data\":[0.19153613,4.4244847,9.864111],\"timestamp\":22849444327000},{\"data\":[-0.34476504,4.2425256,8.858546],\"timestamp\":22849461519000},{\"data\":[-1.3503298,2.633622,6.885724],\"timestamp\":22849481582000},{\"data\":[-1.7046716,1.4748282,7.422025],\"timestamp\":22849501550000},{\"data\":[-1.721431,-0.20111294,8.599973],\"timestamp\":22849521567000},{\"data\":[-2.2888567,-1.1013328,6.6271505],\"timestamp\":22849541454000},{\"data\":[-2.4995465,-0.21068975,4.357447],\"timestamp\":22849563279000},{\"data\":[-1.8866309,1.3215994,4.55856],\"timestamp\":22849581522000},{\"data\":[-0.8714894,1.1587936,4.5441947],\"timestamp\":22849601519000},{\"data\":[-0.15322891,0.6895301,3.7732618],\"timestamp\":22849623325000},{\"data\":[-0.30645782,1.2449849,4.060566],\"timestamp\":22849641602000},{\"data\":[-0.5363012,2.9400797,6.7803793],\"timestamp\":22849661529000},{\"data\":[-0.19153613,3.0933087,9.528923],\"timestamp\":22849681589000},{\"data\":[-0.7374141,2.1547816,10.0269165],\"timestamp\":22849701622000},{\"data\":[-1.1109096,1.9536686,11.932701],\"timestamp\":22849721655000},{\"data\":[-1.436521,1.7046716,12.5743475],\"timestamp\":22849741559000},{\"data\":[-1.6089035,4.175488,14.403518],\"timestamp\":22849761500000},{\"data\":[-1.3503298,4.8841715,17.324444],\"timestamp\":22849781675000},{\"data\":[-1.2976574,2.1739352,17.745823],\"timestamp\":22849801625000},{\"data\":[-2.480393,-2.6144683,13.8959465],\"timestamp\":22849823629000},{\"data\":[-3.7062242,-2.9017725,13.072341],\"timestamp\":22849841700000},{\"data\":[-3.2752678,-1.934515,13.283031],\"timestamp\":22849862237000},{\"data\":[-2.96881,-1.3120226,10.630256],\"timestamp\":22849881512000},{\"data\":[-2.078167,0.58418524,8.983045],\"timestamp\":22849901847000},{\"data\":[-1.685518,1.1971009,8.379706],\"timestamp\":22849923835000},{\"data\":[-1.3790601,2.5378537,9.653421],\"timestamp\":22849941740000},{\"data\":[-1.4269443,3.083732,10.151415],\"timestamp\":22849961482000},{\"data\":[-1.8483237,2.528277,9.509769],\"timestamp\":22849983117000},{\"data\":[-1.6280571,1.733402,9.998186],\"timestamp\":22850001677000},{\"data\":[-1.4748282,0.35434186,10.448297],\"timestamp\":22850021570000},{\"data\":[-1.13964,0.33997664,10.94629],\"timestamp\":22850042325000},{\"data\":[-0.82360536,0.5267244,9.059659],\"timestamp\":22850061477000},{\"data\":[-0.2681506,1.2162545,7.2113357],\"timestamp\":22850081469000},{\"data\":[-0.25617957,0.9864111,4.414908],\"timestamp\":22850105184000},{\"data\":[-0.8331822,1.0247183,2.4612393],\"timestamp\":22850123010000},{\"data\":[-0.8331822,1.4269443,3.5242648],\"timestamp\":22850141939000},{\"data\":[-0.45968673,0.2681506,4.3382936],\"timestamp\":22850165757000},{\"data\":[-0.24899697,-0.17238252,4.1371803],\"timestamp\":22850181659000},{\"data\":[-0.24899697,0.60333884,5.0278234],\"timestamp\":22850201484000},{\"data\":[-0.58418524,2.96881,7.1634517],\"timestamp\":22850223365000},{\"data\":[-1.0726024,2.3654714,8.714894],\"timestamp\":22850241452000},{\"data\":[-1.6184803,2.2984335,11.980585],\"timestamp\":22850261949000},{\"data\":[-2.135628,0.7565677,13.283031],\"timestamp\":22850285939000},{\"data\":[-2.135628,0.7565677,13.637373],\"timestamp\":22850301512000},{\"data\":[-2.5953147,3.3901896,14.5759],\"timestamp\":22850321524000},{\"data\":[-1.2641385,3.7828386,18.368315],\"timestamp\":22850344537000},{\"data\":[-1.0342951,0.8810662,17.353174],\"timestamp\":22850361515000},{\"data\":[-0.9720459,-2.1930888,14.480132],\"timestamp\":22850381734000},{\"data\":[-0.5746084,-3.1411927,11.712435],\"timestamp\":22850402982000},{\"data\":[0.39264908,-2.1068976,11.405977],\"timestamp\":22850422052000},{\"data\":[0.40462008,-0.90021986,9.672575],\"timestamp\":22850441469000},{\"data\":[0.80445176,1.2449849,9.203311],\"timestamp\":22850463117000},{\"data\":[0.545878,3.016694,9.701305],\"timestamp\":22850481500000},{\"data\":[-0.4213795,2.6144683,9.921572],\"timestamp\":22850501579000},{\"data\":[-1.8387469,1.7621324,9.739613],\"timestamp\":22850523189000},{\"data\":[-1.8962077,1.0151415,10.381259],\"timestamp\":22850541630000},{\"data\":[-1.1683705,0.59376204,11.042058],\"timestamp\":22850561525000},{\"data\":[-0.5363012,0.9959879,10.151415],\"timestamp\":22850582310000},{\"data\":[-0.6895301,2.3080103,8.00621],\"timestamp\":22850601477000},{\"data\":[-1.2066777,2.9879637,6.081272],\"timestamp\":22850621615000},{\"data\":[-1.2449849,1.1587936,4.462792],\"timestamp\":22850646072000},{\"data\":[-1.2449849,0.37349546,4.414908],\"timestamp\":22850661674000},{\"data\":[-1.436521,0.17238252,3.7828386],\"timestamp\":22850681487000},{\"data\":[-1.3838485,0.7182605,3.9456444],\"timestamp\":22850702372000},{\"data\":[-0.9864111,0.9959879,4.5681367],\"timestamp\":22850721540000},{\"data\":[-0.5746084,0.9959879,5.2768207],\"timestamp\":22850741722000},{\"data\":[-0.12449849,1.6950948,6.6846113],\"timestamp\":22850761655000},{\"data\":[-0.07182605,2.0398598,7.3645644],\"timestamp\":22850781639000},{\"data\":[-0.22984336,2.0231004,8.542512],\"timestamp\":22850801637000},{\"data\":[-0.8619126,1.532289,9.883265],\"timestamp\":22850825094000},{\"data\":[-0.878672,1.484405,11.511322],\"timestamp\":22850841507000},{\"data\":[-0.5267244,2.2984335,14.738706],\"timestamp\":22850861485000},{\"data\":[-0.78529817,4.1276035,15.667656],\"timestamp\":22850882252000},{\"data\":[-1.1779473,4.8267107,16.816874],\"timestamp\":22850901502000},{\"data\":[-1.6472107,1.8483237,17.353174],\"timestamp\":22850921489000},{\"data\":[-2.2601264,-2.327164,16.864758],\"timestamp\":22850945212000},{\"data\":[-3.1507695,-2.5187001,15.552734],\"timestamp\":22850961844000},{\"data\":[-3.6391866,-2.5019407,12.411542],\"timestamp\":22850981907000},{\"data\":[-3.6391866,-0.6703765,8.829816],\"timestamp\":22851005804000},{\"data\":[-2.5187001,-0.22026655,8.892065],\"timestamp\":22851021564000},{\"data\":[-1.0342951,-0.4405331,8.832211],\"timestamp\":22851041495000},{\"data\":[0.0,0.842759,8.832211],\"timestamp\":22851063119000},{\"data\":[-0.047884032,2.9592333,10.065224],\"timestamp\":22851081562000},{\"data\":[-0.095768064,2.9592333,9.337387],\"timestamp\":22851101575000},{\"data\":[-0.4213795,2.4612393,8.465898],\"timestamp\":22851123159000},{\"data\":[-0.47405192,1.5897499,7.9487495],\"timestamp\":22851141625000},{\"data\":[-0.48362875,1.2545617,7.7572136],\"timestamp\":22851161624000},{\"data\":[-0.6512229,0.9672575,7.5944076],\"timestamp\":22851185164000},{\"data\":[-0.47884035,0.6991069,7.335834],\"timestamp\":22851201494000},{\"data\":[-0.81402856,0.7158663,5.3342814],\"timestamp\":22851221972000},{\"data\":[-1.2545617,0.41180268,3.466804],\"timestamp\":22851242300000},{\"data\":[-1.1923125,1.0342951,5.6407394],\"timestamp\":22851261459000},{\"data\":[-0.7374141,0.9816227,5.985504],\"timestamp\":22851281772000},{\"data\":[-0.7541735,1.0319009,4.3287168],\"timestamp\":22851301934000},{\"data\":[-0.9672575,1.484405,5.7843914],\"timestamp\":22851322845000},{\"data\":[-0.9816227,2.5761611,8.676587],\"timestamp\":22851341499000},{\"data\":[-1.0414777,2.3654714,10.601525],\"timestamp\":22851363172000},{\"data\":[-0.9744401,2.1260512,12.248735],\"timestamp\":22851381549000},{\"data\":[-0.89782566,1.3311762,12.603078],\"timestamp\":22851401549000},{\"data\":[-0.89782566,1.388637,14.18325],\"timestamp\":22851424857000},{\"data\":[-1.1587936,2.231396,14.997279],\"timestamp\":22851441564000},{\"data\":[-1.4269443,3.1986535,14.805743],\"timestamp\":22851461587000},{\"data\":[-2.0590134,3.821146,13.2351465],\"timestamp\":22851482585000},{\"data\":[-1.7525556,1.8962077,13.599066],\"timestamp\":22851501547000},{\"data\":[-1.5131354,-1.7717092,13.64695],\"timestamp\":22851522062000},{\"data\":[-0.12449849,-4.175488,15.600618],\"timestamp\":22851544619000},{\"data\":[0.9864111,-3.6008794,13.723564],\"timestamp\":22851561547000},{\"data\":[1.3694834,-1.6759412,9.471462],\"timestamp\":22851581477000},{\"data\":[1.2162545,1.4460979,8.360553],\"timestamp\":22851601587000},{\"data\":[1.2545617,3.1411927,9.414001],\"timestamp\":22851621559000},{\"data\":[1.2401965,2.4516625,10.189722],\"timestamp\":22851641815000},{\"data\":[0.35434186,1.7908629,9.615114],\"timestamp\":22851662022000},{\"data\":[-0.4405331,0.90979666,9.203311],\"timestamp\":22851681877000},{\"data\":[-0.9768343,0.5554548,8.772355],\"timestamp\":22851701775000},{\"data\":[-1.292869,2.0015526,8.523358],\"timestamp\":22851726235000},{\"data\":[-1.1300632,1.6759412,7.297527],\"timestamp\":22851741550000},{\"data\":[-1.1468226,1.1971009,5.2959743],\"timestamp\":22851761520000},{\"data\":[-0.79487497,0.78529817,5.085284],\"timestamp\":22851783227000},{\"data\":[-1.4269443,0.79726917,4.5681367],\"timestamp\":22851801512000},{\"data\":[-1.4939818,1.2737153,4.414908],\"timestamp\":22851821919000},{\"data\":[-1.4939818,0.9576807,3.9264908],\"timestamp\":22851841549000},{\"data\":[-0.7182605,-0.24899697,4.5777135],\"timestamp\":22851861470000},{\"data\":[-0.15322891,0.095768064,5.9184666],\"timestamp\":22851881899000},{\"data\":[-0.10534488,2.2505496,7.2113357],\"timestamp\":22851904982000},{\"data\":[-0.10534488,3.323152,7.747637],\"timestamp\":22851921565000},{\"data\":[-0.16759412,3.2800562,8.532935],\"timestamp\":22851941529000},{\"data\":[-0.641646,2.1739352,9.136273],\"timestamp\":22851961750000},{\"data\":[-1.283292,1.6663644,11.022904],\"timestamp\":22851981557000},{\"data\":[-1.5993267,2.3080103,13.283031],\"timestamp\":22852001490000},{\"data\":[-0.89064306,3.1028855,15.121778],\"timestamp\":22852022410000},{\"data\":[-0.82360536,3.9360676,16.184803],\"timestamp\":22852041474000},{\"data\":[-1.5035586,3.5625722,15.705963],\"timestamp\":22852061639000},{\"data\":[-2.2218192,1.6663644,16.989256],\"timestamp\":22852081565000},{\"data\":[-2.1739352,-1.1109096,19.086576],\"timestamp\":22852101492000},{\"data\":[-2.6623523,-2.825158,16.098612],\"timestamp\":22852122244000},{\"data\":[-2.920926,-2.432509,12.354081],\"timestamp\":22852142807000},{\"data\":[-2.7102363,-1.5801731,9.80665],\"timestamp\":22852161650000},{\"data\":[-2.3750482,0.25857377,8.312668],\"timestamp\":22852181490000},{\"data\":[-2.030283,0.8523358,7.699753],\"timestamp\":22852201520000},{\"data\":[-1.532289,0.90979666,8.762778],\"timestamp\":22852221574000},{\"data\":[-1.3503298,0.5746084,7.833828],\"timestamp\":22852241714000},{\"data\":[-1.364695,0.39264908,7.249643],\"timestamp\":22852262862000},{\"data\":[-1.0151415,0.6991069,8.628703],\"timestamp\":22852281687000},{\"data\":[-0.40222588,1.4269443,9.768343],\"timestamp\":22852301844000},{\"data\":[0.019153614,1.4652514,8.743625],\"timestamp\":22852325620000},{\"data\":[-0.019153614,1.4077905,6.981492],\"timestamp\":22852341525000},{\"data\":[-0.5746084,1.460463,4.3478703],\"timestamp\":22852361465000},{\"data\":[-0.521936,1.9919758,4.807557],\"timestamp\":22852383070000},{\"data\":[-0.46686932,1.1492168,4.735731],\"timestamp\":22852401510000},{\"data\":[-0.45489833,0.9864111,4.735731],\"timestamp\":22852421482000},{\"data\":[-0.8810662,1.1683705,4.156334],\"timestamp\":22852445312000},{\"data\":[-0.89303726,1.0055647,5.085284],\"timestamp\":22852461867000},{\"data\":[-0.93852705,1.3215994,6.8186865],\"timestamp\":22852481915000},{\"data\":[-0.93852705,1.3215994,8.379706],\"timestamp\":22852501887000},{\"data\":[-1.5705963,2.6527755,10.113108],\"timestamp\":22852522754000},{\"data\":[-2.135628,3.1316159,10.160992],\"timestamp\":22852541504000},{\"data\":[-1.8195933,2.4899697,11.386824],\"timestamp\":22852562342000},{\"data\":[-1.6280571,1.388637,12.11466],\"timestamp\":22852581519000},{\"data\":[-1.6424223,1.5514427,12.74673],\"timestamp\":22852601559000},{\"data\":[-2.231396,3.3901896,12.694057],\"timestamp\":22852622367000},{\"data\":[-2.231396,4.367024,13.417107],\"timestamp\":22852641497000},{\"data\":[-1.9057846,3.8594532,14.298173],\"timestamp\":22852661479000},{\"data\":[-1.7046716,1.9632454,12.890382],\"timestamp\":22852684592000},{\"data\":[-1.8579005,-0.30645782,12.933477],\"timestamp\":22852701699000},{\"data\":[-1.8148049,-1.0821792,16.117765],\"timestamp\":22852722204000},{\"data\":[-1.1013328,-0.60333884,16.424223],\"timestamp\":22852742812000},{\"data\":[-0.8523358,0.1436521,12.239159],\"timestamp\":22852761657000},{\"data\":[-0.37349546,0.45968673,10.400413],\"timestamp\":22852781527000},{\"data\":[-0.35673606,-0.25857377,10.457873],\"timestamp\":22852801554000},{\"data\":[-0.545878,-1.2066777,10.457873],\"timestamp\":22852821615000},{\"data\":[-0.545878,-1.484405,10.390836],\"timestamp\":22852841517000},{\"data\":[-0.019153614,-0.47884035,9.80665],\"timestamp\":22852862300000},{\"data\":[0.50757074,-0.47884035,8.379706],\"timestamp\":22852881477000},{\"data\":[0.8619126,-0.78529817,7.8529816],\"timestamp\":22852901805000},{\"data\":[0.878672,0.16280572,9.279925],\"timestamp\":22852922925000},{\"data\":[0.4309563,1.0630256,8.868123],\"timestamp\":22852941969000},{\"data\":[-0.06703765,1.4269443,7.632715],\"timestamp\":22852961479000},{\"data\":[-0.1340753,1.3742718,7.3645644],\"timestamp\":22852982364000},{\"data\":[-0.50757074,0.7565677,7.3645644],\"timestamp\":22853001510000},{\"data\":[-0.94810385,0.41180268,7.0485296],\"timestamp\":22853021845000},{\"data\":[-1.4173675,0.6512229,7.0485296],\"timestamp\":22853044564000},{\"data\":[-1.2641385,1.1587936,7.5561004],\"timestamp\":22853061720000},{\"data\":[-0.7661445,1.1468226,7.3166804],\"timestamp\":22853081607000},{\"data\":[-0.41180268,1.5131354,8.149862],\"timestamp\":22853105099000},{\"data\":[-0.41180268,1.9728222,8.485051],\"timestamp\":22853121942000},{\"data\":[-1.1587936,2.9879637,8.418014],\"timestamp\":22853141517000},{\"data\":[-0.5363012,2.6240451,9.174581],\"timestamp\":22853162314000},{\"data\":[0.1340753,1.388637,8.590396],\"timestamp\":22853181535000},{\"data\":[0.39264908,-0.4309563,8.810662],\"timestamp\":22853201545000},{\"data\":[-0.25857377,0.31603462,9.959879],\"timestamp\":22853223005000},{\"data\":[-0.80445176,1.4269443,8.762778],\"timestamp\":22853241554000},{\"data\":[-0.7469909,1.4269443,7.8146744],\"timestamp\":22853261544000},{\"data\":[-1.1683705,1.8866309,8.350976],\"timestamp\":22853283130000},{\"data\":[-1.1109096,1.9033904,10.036493],\"timestamp\":22853301517000},{\"data\":[-1.163582,1.7142484,10.697293],\"timestamp\":22853321885000},{\"data\":[-1.0893618,0.7469909,10.055647],\"timestamp\":22853344612000},{\"data\":[-1.0989386,1.5897499,9.442732],\"timestamp\":22853361574000},{\"data\":[-0.047884032,2.078167,9.77792],\"timestamp\":22853381600000},{\"data\":[-0.6799533,3.217807,9.816227],\"timestamp\":22853403855000},{\"data\":[-1.2545617,3.4093432,8.848969],\"timestamp\":22853422430000},{\"data\":[-0.6991069,3.4476504,9.500193],\"timestamp\":22853441672000},{\"data\":[-0.6368576,2.336741,8.552089],\"timestamp\":22853465560000},{\"data\":[-0.81402856,2.767697,7.967903],\"timestamp\":22853481492000},{\"data\":[-1.1300632,2.135628,8.197746],\"timestamp\":22853501490000},{\"data\":[-0.5554548,1.9057846,10.572795],\"timestamp\":22853522469000},{\"data\":[-0.2681506,1.541866,9.279925],\"timestamp\":22853541510000},{\"data\":[-1.283292,2.6431987,8.331821],\"timestamp\":22853561484000},{\"data\":[-0.7757214,2.6431987,9.509769],\"timestamp\":22853584467000},{\"data\":[-0.35434186,2.1930888,10.850522],\"timestamp\":22853601562000},{\"data\":[-0.8810662,2.207454,10.342952],\"timestamp\":22853622580000},{\"data\":[-1.4939818,2.96881,8.781932],\"timestamp\":22853642765000},{\"data\":[-0.8714894,2.6144683,8.793902],\"timestamp\":22853661650000},{\"data\":[-0.59376204,2.1643584,9.088389],\"timestamp\":22853681490000},{\"data\":[-0.076614454,2.1787236,10.5440645],\"timestamp\":22853701552000},{\"data\":[-0.0047884034,2.1140802,10.5440645],\"timestamp\":22853721582000},{\"data\":[-0.9576807,2.9400797,8.475474],\"timestamp\":22853741499000},{\"data\":[-1.2449849,2.9400797,7.929596],\"timestamp\":22853763102000},{\"data\":[-1.0151415,2.882619,10.515334],\"timestamp\":22853781545000},{\"data\":[-0.9672575,2.0111294,10.342952],\"timestamp\":22853801467000},{\"data\":[-0.641646,1.8100165,9.212888],\"timestamp\":22853826282000},{\"data\":[-0.6536171,2.3558946,8.42759],\"timestamp\":22853841922000},{\"data\":[-0.6536171,3.064578,9.030929],\"timestamp\":22853861479000},{\"data\":[0.30645782,2.6431987,10.496181],\"timestamp\":22853882400000},{\"data\":[-0.1340753,2.7006595,8.628703],\"timestamp\":22853901505000},{\"data\":[0.06703765,2.6623523,8.848969],\"timestamp\":22853921565000},{\"data\":[-0.81402856,2.96881,9.193734],\"timestamp\":22853944569000},{\"data\":[-0.4309563,2.9592333,10.151415],\"timestamp\":22853961692000},{\"data\":[-0.25857377,2.2601264,9.797073],\"timestamp\":22853981695000},{\"data\":[-0.2705448,2.2218192,9.969456],\"timestamp\":22854002784000},{\"data\":[-0.6991069,2.4229321,9.078813],\"timestamp\":22854022437000},{\"data\":[-0.7565677,2.882619,8.772355],\"timestamp\":22854041499000},{\"data\":[-0.038307227,2.6144683,10.5440645],\"timestamp\":22854064560000},{\"data\":[-0.48841715,2.6527755,9.509769],\"timestamp\":22854081589000},{\"data\":[-0.8331822,2.669535,8.532935],\"timestamp\":22854101644000},{\"data\":[-0.81881696,2.7365725,8.8777],\"timestamp\":22854125822000},{\"data\":[-0.37349546,2.4899697,10.04607],\"timestamp\":22854141509000},{\"data\":[-0.10534488,2.4277205,10.5919485],\"timestamp\":22854161537000},{\"data\":[-0.5363012,2.4277205,8.858546],\"timestamp\":22854183147000},{\"data\":[-0.79487497,2.6719291,8.872911],\"timestamp\":22854201507000},{\"data\":[-0.641646,2.873042,9.844957],\"timestamp\":22854221789000},{\"data\":[-0.41180268,2.2409728,10.141838],\"timestamp\":22854244769000},{\"data\":[-0.6512229,1.9728222,9.308656],\"timestamp\":22854261515000},{\"data\":[-1.1587936,1.9201498,8.169016],\"timestamp\":22854281595000},{\"data\":[-0.842759,2.4133554,10.141838],\"timestamp\":22854302620000},{\"data\":[-0.50757074,2.719813,10.342952],\"timestamp\":22854322070000},{\"data\":[-0.50757074,2.2122424,8.829816],\"timestamp\":22854341634000},{\"data\":[-0.5793968,2.030283,8.1881695],\"timestamp\":22854364824000},{\"data\":[-0.8523358,2.432509,9.452309],\"timestamp\":22854381499000},{\"data\":[-0.4309563,2.6719291,10.582372],\"timestamp\":22854401559000},{\"data\":[-0.44771573,2.6839,9.854534],\"timestamp\":22854422319000},{\"data\":[-0.8331822,2.1164744,8.63828],\"timestamp\":22854441560000},{\"data\":[-0.8331822,1.7621324,9.883265],\"timestamp\":22854461640000},{\"data\":[-0.8451532,1.5131354,9.89763],\"timestamp\":22854483062000},{\"data\":[-0.8451532,2.7581203,9.548077],\"timestamp\":22854501549000},{\"data\":[-0.6607997,3.1028855,9.241618],\"timestamp\":22854521470000}],\"device\":\"Android Combo\",\"type\":\"INERTIAL\",\"timestamp\":1521016634029,\"sensorDelay\":\"FASTEST\",\"sensors\":\"name: MMA8452 3-axis Accelerometer, resolution: 0.038307227, maximumRange: 19.6133, type: 1, vendor: Freescale Semiconductor Inc., version: 43701\",\"displacement\":0.0,\"steps\":12}\n"
    }

    lateinit var data: InertialDataset

    @Before
    fun setUp() {
        data = ObjectMapper().readValue<InertialDataset>(TEST_DATA_JSON.toByteArray(), object : TypeReference<InertialDataset>() {})
    }

    @Test
    fun addSample() {
        val pedometer = Pedometer()
        for (acceleration in data.acceleration) {
            pedometer.processSample(acceleration)
        }

        assertEquals(data.steps, pedometer.stepCount)
    }

}