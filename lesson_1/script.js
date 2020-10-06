function createTable(parent, data) {
  var br = document.createElement('br');
  parent.appendChild(br);
  var table = document.createElement('table');
  table.setAttribute('align', 'center');
  for (let key in data) {
    var tr = document.createElement('tr');
    for (var i = 0; i < 2; i++) {
      var td = document.createElement('td');
      if (i == 0) {
        td.innerHTML = key;
      } else if (i == 1) {
        td.innerHTML = data[key];
      }
      tr.appendChild(td);
    }
    table.appendChild(tr);
  }

  parent.appendChild(table);

}

function clearDiv() {
  var elem = document.querySelectorAll('#elem');
  var parent = document.querySelector('#body');
  var inp = document.querySelector('#id1').value= "";
  var elems = elem[0];
  elems.remove();
  var div = document.createElement('div');
  div.setAttribute('id', 'elem');
  parent.appendChild(div);


}
