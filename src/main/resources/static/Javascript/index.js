'use strict';
const tracks = document.querySelector('#tracks');
const readmeBtn = document.querySelector('#readMe');
const createBtn = document.querySelector('#createSubmit');
const updateBtn = document.querySelector('#updateTrack');
const clearBtn = document.querySelector('#clear');
const deletebtn = document.querySelector('#delButton');

const trackName = document.querySelector('#trackName');
const trackArtist = document.querySelector('#trackArtist');
const trackLength = document.querySelector('#trackLength');
const mixId = document.querySelector('#mixId');

const trackId = document.querySelector('#trackId');
const updatetrackName = document.querySelector('#updatetrackName');
const updatedTrackArtist = document.querySelector('#updatedTrackArtist');
const updatedTrackLength = document.querySelector('#updatedTrackLength');
const updatedMixId = document.querySelector('#updatedMixId');

const delTrack = document.querySelector('#deleteTrack');


const BASE_URL = "http://localhost:8081/tracks/readAll";
const POST_URL = "http://localhost:8081/tracks/create";


const retrieveTrackData = () => {
    fetch(BASE_URL)
        .then((response) => {
            console.log(response);
            if (response.status !== 200) {
                console.error('Looks like there was a problem. Status code: ' +
                response.status);

                return;
            } else {
                response.json().then(json => {
                    console.log(json);

                    for (let i = 0; i < json.length; i++) {
                        let p = document.createElement("p");
                        p.setAttribute("class", "data");
                        let info = document.createTextNode(json[i].id + " " + json[i].trackName + " " + json[i].trackArtist);
                        p.appendChild(info);
                        tracks.appendChild(p);
                    }
                })
            }
        })
        .catch(err => console.error(`Stop! ${err}`));
}

const createTrack = () => {
    console.log(JSON.stringify({
        trackArtist: trackName.value,
        trackLength: trackLength.value,
        trackName: trackArtist.value,
        mixes: {id: mixId.value }
    }))
    fetch(POST_URL, {
        method: 'POST',
        body: JSON.stringify({
            trackArtist: trackName.value,
            trackLength: trackLength.value,
            trackName: trackArtist.value,
            mixes: {id: mixId.value }
        }),
        headers: {
            'Content-Type': "application/json"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.error(`Seomthing went wrong` + err));
}

createBtn.addEventListener('click', createTrack);

const updateTrack = () => {
    console.log(JSON.stringify ({
        id: trackId.value,
        trackArtist: updatedTrackArtist.value,
        trackLength: updatedTrackLength.value,
        trackName: updatetrackName.value,
        mixes: {id: updatedMixId.value}
    }))
        fetch("http://localhost:8081/tracks/update/"+trackId.value, {
            method: 'PUT',
            body: JSON.stringify ({
                trackArtist: updatedTrackArtist.value,
                trackLength: updatedTrackLength.value,
                trackName: updatetrackName.value,
                mixes: {id: updatedMixId.value}
            }),
            headers: {
                'Content-Type': "application/json"
            }
        })
            .then(response => response.json())
            .then(json => console.log(json))
            .catch(err => console.error(`Seomthing went wrong` + err))
            .catch(alert(`INCORRECT VALUE PASSED... PLEASE TRY AGAIN WITH A VALID TRACK ID IN THE DATABASE`));

}

    

const deleteTrack = () => {
    fetch("http://localhost:8081/tracks/delete/"+delTrack.value, {
        method: 'DELETE',
        body: JSON.stringify({
            id: delTrack.value
        }),
        headers: {
            'Content-Type': "application/json"
        }
    })
    .then(response => response.json())
    .then(json => console.log(json))
    .catch(err => console.error(`Seomthing went wrong` + err));
}

const clearHistory = () => {
    tracks.innerHTML = "";
};

readmeBtn.addEventListener('click', retrieveTrackData);
clearBtn.addEventListener('click', clearHistory);
updateBtn.addEventListener('click', updateTrack);
deletebtn.addEventListener('click', deleteTrack);