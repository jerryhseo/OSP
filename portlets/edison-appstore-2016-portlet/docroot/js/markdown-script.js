// Code goes here

$(function() {
  var $previewContainerKr = $('.comment-md-preview-container_ko_KR');
  /*$previewContainerKr.show();*/
  var $md = $(".comment-md_ko_KR").markdown({
    autofocus: false,
    height: 270,
    iconlibrary: 'fa',
    onShow: function(e) {
      //e.hideButtons('cmdPreview');
      e.change(e);
    },
    onChange: function(e) {
      var content = e.parseContent();
      if (content === '') {
    	  /*$previewContainerKr.show();*/
      } else {
    	  $previewContainerKr.show().find('.comment-md-preview_ko_KR').html(content).find('table').addClass('table table-bordered table-striped table-hover');
      }
    },
    footer: function(e) {
      return '\
					<span class="text-muted">\
						<span data-md-footer-message="err">\
						</span>\
						<span data-md-footer-message="default">\
						</span>\
					</span>';
    }
  });
  
  var $previewContainerEn = $('.comment-md-preview-container_en_US');
  /*$previewContainerEn.show();*/
  var $md = $(".comment-md_en_US").markdown({
    autofocus: false,
    height: 270,
    iconlibrary: 'fa',
    onShow: function(e) {
      //e.hideButtons('cmdPreview');
      e.change(e);
    },
    onChange: function(e) {
      var content = e.parseContent();
      if (content === '') {
    	  /*$previewContainerEn.show();*/
      } else {
    	  $previewContainerEn.show().find('.comment-md-preview_en_US').html(content).find('table').addClass('table table-bordered table-striped table-hover');
      }
    },
    footer: function(e) {
      return '\
					<span class="text-muted">\
						<span data-md-footer-message="err">\
						</span>\
						<span data-md-footer-message="default">\
						</span>\
					</span>';
    }
  });

  var $mdEditor = $('.md-editor'),
    msgs = {};

  $mdEditor.find('[data-md-footer-message]').each(function() {
    msgs[$(this).data('md-footer-message')] = $(this).hide();
  });
  msgs.
  default.show();
  $mdEditor.filedrop({
    binded_input: $('#comment-images'),
    url: "static-uploads.php",
    fallbackClick: false,
    beforeSend: function(file, i, done) {
      msgs.
      default.hide();
      msgs.err.hide();
      msgs.loading.show();
      done();
    },
    //maxfilesize: 15,
    error: function(err, file) {
      switch (err) {
        case 'BrowserNotSupported':
          alert('browser does not support HTML5 drag and drop')
          break;
        case 'FileExtensionNotAllowed':
          // The file extension is not in the specified list 'allowedfileextensions'
          break;
        default:
          break;
      }
      var filename = typeof file !== 'undefined' ? file.name : '';
      msgs.loading.hide();
      msgs.err.show().html('<span class="text-danger"><i class="fa fa-times"></i> Error uploading ' + filename + ' - ' + err + '</span><br />');
    },
    dragOver: function() {
      $(this).addClass('active');
    },
    dragLeave: function() {
      $(this).removeClass('active');
    },
    progressUpdated: function(i, file, progress) {
      msgs.loading.html('<i class="fa fa-refresh fa-spin"></i> Uploading <span class="text-info">' + file.name + '</span>... ' + progress + '%');
    },
    afterAll: function() {
      msgs.
      default.hide();
      msgs.loading.hide();
      msgs.err.hide();
    },
    uploadFinished: function(i, file, response, time) {
      $md.val($md.val() + "![" + file.name + "](http://photography.naturestocklibrary.com/orca-stock-photo.jpg)\n").trigger('change');
      // response is the data you got back from server in JSON format.
    }
  });
})