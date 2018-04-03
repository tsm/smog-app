package com.tomszom.smogapp.city.measure

/**
 * Created by tsm on 03/04/2018
 */
internal enum class MeasureParticle constructor(val formula: String,
                                                val unit: String,
                                                val threshold1: Double,
                                                val threshold2: Double,
                                                val threshold3: Double,
                                                val threshold4: Double,
                                                val threshold5: Double
) {

    SO2("SO2", "μg/m3", 50.0, 100.0, 200.0, 350.0, 500.0),
    NO2("NO2", "μg/m3", 40.0, 100.0, 150.0, 200.0, 400.0),
    CO("CO", "μg/m3", 2499.0, 6499.0, 10499.0, 14499.0, 20499.0),
    PM10("PM10", "μg/m3", 20.0, 60.0, 100.0, 140.0, 200.0),
    PM25("PM2.5", "μg/m3", 12.0, 36.0, 60.0, 84.0, 120.0),
    O3("O3", "μg/m3", 30.0, 70.0, 120.0, 160.0, 240.0),
    C6H6("C6H6", "μg/m3", 5.0, 10.0, 15.0, 20.0, 50.0)

}