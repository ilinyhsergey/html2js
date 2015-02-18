// js header
{{ for}}
    var fileName = '{{name }}';
    var filecontent = {{ content }};
{{
    end
}}
var names = [];
{{for}}names.push('{{name}}');{{end}}
console.log(names);
// js footer