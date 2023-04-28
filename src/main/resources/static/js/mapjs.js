console.log("this is message comming from mapjs.js")

        const labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                let labelIndex = 0;

        function initMap()
        {
            var uluru = { lat: 40.617314, lng: -99.612553 };

            // 40.617314, -99.612553
            var map = new google.maps.Map(document.getElementById('map'), { zoom: 3, center: uluru });


            var tourCoordinates = [];

            for (i = 0; i < listCities.length; i++)
            {

                console.log(listCities.length);
                console.log(i)

                tourCoordinates.push({ lat: listCities[i].latitude, lng: listCities[i].longitude })

                console.log("Lon: " + listCities[i].longitude);
                console.log("Lat: " + listCities[i].latitude);

                var marker = new google.maps.Marker({
                    position: { lat: listCities[i].latitude, lng: listCities[i].longitude },
                    label: labels[labelIndex++ % labels.length],
                    map: map
                });
            }


            const flightPath = new google.maps.Polyline({
                            path: tourCoordinates,
                            geodesic: true,
                            strokeColor: "#FF0000",
                            strokeOpacity: 1.0,
                            strokeWeight: 2,
                        });

                        flightPath.setMap(map);
        }