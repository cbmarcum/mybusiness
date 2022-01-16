<h3 id="editor">HTML Editor</h3>

<p>
    <b>HTML Editor</b> is a WYSIWYG (what you see is what you get) HTML editor used in places where HTML data
needs to be entered and stored in the database like in product descriptions and blog entries. In the editor
you can switch between source code and WYSIWYG modes. In WYSIWYG mode there are a number of
formatting tools similar to what you would find in a word processor.
</p>

<p>
    <b>Images</b> can be added by uploading to them the server or by providing a link to an external
image hosted elsewhere like Amazon s3 storage.<br/>
    To make images responsive and scale properly on smaller devices:
<ul>
    <li>use images of the correct size when viewing on desktop computer without scaling.
    For an image, that means about 700 pixels wide maximum.</li>
    <li>Add the CSS style class <b>img-responsive</b> to image.</li>
</ul>

<p>
    Caution: Images uploaded using the editor are stored on the server's filesystem and could
    lost between backups in the unlikely event of a catastrophic server failure.
</p>
