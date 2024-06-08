
function toggleEvent(table, index, value) {
	 if (value == 'on') {
		 table.column(index).visible(false);
	 } else {
		 table.column(index).show();
	 }
	 table.draw();
}
function toggleSingle(table, index, col) {

	$('.toggle-control:eq('+index+')').on( 'change', function () {
		let x = $(this).val();
		toggleEvent(table, col, x);
	 });
}
$(document).ready( function () {

	var table = $('#dataTable').DataTable({
    // hides search box
    // searching: false,
			"sAjaxSource": "/all",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
          {
            "mData": "lang",
            render: function (data, type) {
              return renderColumn(data);
            }
          },
          {
            "mData": "platform",
            render: function (data, type) {
              return renderColumn(data);
            }
          },
          {
            "mData": "build",
            render: function (data, type) {
              return renderColumn(data);
            }
          },
          {
            "mData": "tech",
            render: function (data, type) {
              return renderColumn(data);
            }
          },
			    { "mData": "name"},
			    { "mData": "description"},
    			{
            "mData": "keywords",
            render: function (data, type) {
              var opt = '';
        			$.each(data, function (index, val) {
                opt += val.name + ',';
								if (index%3 == 0 && index != 0) {
									opt += '\n';
								}
        			});
              return opt;
            }
          }
			]
	 });

	 // 'All' toggle event
	 $('.toggle-control:eq(0)').on( 'change', function () {
		 let x = $(this).val();
		 toggleEvent(table, 0, x);
		 toggleEvent(table, 1, x);
		 toggleEvent(table, 2, x);
		 toggleEvent(table, 3, x);
		 toggleEvent(table, 5, x);
		 toggleEvent(table, 6, x);
 		});


		for (var i=1;i<8;i++) {
			var col = i-1;
			if (i > 3)
				col = i + 1;
			toggleSingle(table, i, col);
		}
//   new LangMenu('/menu/lang', 'lang');

//	 new TechMenu('/menu/tech', 'tech');

   new ColorFilter(table, '/api/filter/lang', 0);

   new ColorFilter(table, '/api/filter/platform', 1);

   new ColorFilter(table, '/api/filter/build', 2);

	 new ColorFilter(table, '/api/filter/tech', 3);

   new KeywordsFilter(table, '/filter/keywords', 4);
});
