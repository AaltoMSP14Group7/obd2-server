#Database structure

The data points live in the database obd_db and it's collection, datapoints.

Each individual data point (document in the Mongo collection) is as follows:  
  
{  
    deviceId [string] // A unique 32-byte identifier hash  
                       // for the mobile device  
    vin [string] // International Vehicle Identification Number  
    timestamp [int] // Unix timestamp - seconds since epoch  
    type [string] // Indicates what the value is  
    value [varies: string, array, float...]  
}  
 
###Examples:  
  
>ATTENTION: We may also log other data (fault codes, software errors etc.) in the database in addition to these examples, so make sure they won’t mess up your code.#

// The user has started tracking - the first data point of a session  
{  
    deviceId: “8555937c55a3[...snip...]f24d1e012d4b92”,  
    vin: “WP0ZZZ99ZTS392124”,  
    timestamp: 1394794579,  
    type: “tracking_started”,  
    value: true // Boolean: Always true  
}  

// Fuel remaining  
// ATTENTION: This may not be possible to get - we’ll let you know  
{  
    deviceId: “8555937c55a3[...snip...]f24d1e012d4b92”,  
    vin: “WP0ZZZ99ZTS392124”,  
    timestamp: 1394794579,  
    type: “fuel_remaining”,  
    value: 20.3 // Double: Liters  
}  
  
// Instant fuel consumption  
{  
    deviceId: “8555937c55a3[...snip...]f24d1e012d4b92”,  
    vin: “WP0ZZZ99ZTS392124”,  
    timestamp: 1394794579,  
    type: “fuel_consumption”,  
    value: 4.1 // Double: Liters / kilometer  
}  

// Vehicle speed (from OBD2)  
{  
    deviceId: “8555937c55a3[...snip...]f24d1e012d4b92”,  
    vin: “WP0ZZZ99ZTS392124”,  
    timestamp: 1394794579,  
    type: “vehicle_speed”,  
    value: 81 // Integer: km/h  
}  
  
// Distance traveled - we’ll get this either from obd2 or if that’s  
// not possible than the GPS  
{  
    deviceId: “8555937c55a3[...snip...]f24d1e012d4b92”,  
    vin: “WP0ZZZ99ZTS392124”,  
    timestamp: 1394794579,  
    type: “distance_traveled”,  
    value: 121.4 // Double: kilometers  
}  
  
// Location of the phone as degrees lat/long. May have negative  
// values!  
{  
    deviceId: “8555937c55a3[...snip...]f24d1e012d4b92”,  
    vin: “WP0ZZZ99ZTS392124”,  
    timestamp: 1394794579, 
    type: “location”,  
    value: [
        60.18687271299999, // Double: latitude  
        24.821316153999994 // Double: longitude  
    ]
}  
  
// The user has stopped the tracking - last data point of a session  
// This may not be present e.g. if the battery has run out during  
// tracking.  
{  
    deviceId: “8555937c55a3[...snip...]f24d1e012d4b92”,  
    vin: “WP0ZZZ99ZTS392124”,  
    timestamp: 1394794579,  
    type: “tracking_ended”,  
    value: true // Boolean: Always true  
}  
