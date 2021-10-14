const albumsList = document.getElementById('albumsList')
const searchBar = document.getElementById('searchInput')

const allAlbums = [];

fetch("http://localhost:8000/dogs/api").
then(response => response.json()).
then(data => {
  for (let album of data) {
    allAlbums.push(album);
  }
})

console.log(allAlbums);

searchBar.addEventListener('keyup', (e) => {
  const searchingCharacters = searchBar.value.toLowerCase();
  let filteredAlbums = allAlbums.filter(album => {
    return album.name.toLowerCase().includes(searchingCharacters)||
        album.breed.toLowerCase().includes(searchingCharacters)

  });
  displayAlbums(filteredAlbums);
})


const displayAlbums = (albums) => {
  albumsList.innerHTML = albums
      .map((a) => {
        return ` <div class="col-md-3" >
                <div class="card mb-4 box-shadow">
                <img src="${a.picture}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height: 225px; width: 100%; display: block;">
                <div class="card-body">
                    <div class="text-center">
                        <p class="card-text border-bottom ">Name: ${a.name}</p>
                        <p class="card-text border-bottom ">Breed: ${a.breed}</p>
                        <p class="card-text border-bottom ">Sex: ${a.sex}</p>
                                                                       
                    </div>
                    <div class="d-flex justify-content-between align-items-center">
                       
                    </div>
                </div>
            </div>
            </div>`
      })
      .join('');

}