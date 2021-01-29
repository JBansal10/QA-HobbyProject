'use strict';
const mixes = document.querySelector('#mixes');
const readmeBtn = document.querySelector('#readAllMix');
const createBtn = document.querySelector('#createMix');
const updateBtn = document.querySelector('#updateMix');
const clearBtn = document.querySelector('#clear');
const deletebtn = document.querySelector('#deleteMixBtn');

const mixName = document.querySelector('#mixName');
const mixLength = document.querySelector('#mixLength');

const mixId = document.querySelector('#mixId');
const updateMixName = document.querySelector('#updateMixName');
const updateMixLength = document.querySelector('#updateMixLength');


const delTrack = document.querySelector('#deleteTrack');


const BASE_URL = "http://localhost:8081/mixes/readAll";
const POST_URL = "http://localhost:8081/mixes/create";


const retrieveMixData = () => {
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
                        let info = document.createTextNode(json[i].id + " " + json[i].mixName + " " + json[i].mixLength);
                        p.appendChild(info);
                        mixes.appendChild(p);
                    }
                })
            }
        })
        .catch(err => console.error(`Stop! ${err}`));
}

const createTrack = () => {
    console.log(JSON.stringify({
        mixName: mixName.value,
        mixLength: mixLength.value,
    }))
    fetch(POST_URL, {
        method: 'POST',
        body: JSON.stringify({
            mixName: mixName.value,
            mixLength: mixLength.value,
        }),
        headers: {
            'Content-Type': "application/json"
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
        .catch(err => console.error(`Seomthing went wrong` + err));
}

const updateMix = () => {
    console.log(JSON.stringify ({
        id: mixId.value,
        trackArtist: updateMixName.value,
        mixLength: updateMixLength.value
    }))
        fetch("http://localhost:8081/mixes/update/"+mixId.value, {
            method: 'PUT',
            body: JSON.stringify ({
                mixName: updateMixName.value,
                mixLength: updateMixLength.value
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

    

const deleteMix = () => {
    fetch("http://localhost:8081/mixes/delete/"+delMix.value, {
        method: 'DELETE',
        body: JSON.stringify({
            id: delMix.value
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
    mixes.innerHTML = "";
};

readmeBtn.addEventListener('click', retrieveMixData);
createBtn.addEventListener('click', createTrack);
clearBtn.addEventListener('click', clearHistory);
updateBtn.addEventListener('click', updateMix);
deletebtn.addEventListener('click', deleteMix);