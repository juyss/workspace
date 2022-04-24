var fs = require('fs');
var path = require('path')

var file = {
	/**
	 * 读文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param callbackFn
	 *            回调函数
	 * @returns 若无回调函数，直接返回字符串
	 */
	read: function(filePath, callbackFn) {
		if (/\/$/.test(filePath)) {
			if (!callbackFn) {
				throw new Error('callbackFn can not be null');
			}

			fs.readdir(filePath, function(err, files) {
				if (err) {
					throw err;
				}

				callbackFn(files);
			});
		} else if (callbackFn) {
			fs.readFile(filePath, 'utf8', function(err, data) {
				if (err) {
					throw err;
				}

				callbackFn(data);
			});
		} else {
			return fs.readFileSync(filePath, 'utf8');
		}
	},
	readLine: function(filePath, callbackFn) {
		var input = fs.createReadStream(filePath), remaining = '';

		input.on('data', function(data) {
			remaining += data;
			var index = remaining.indexOf('\n');
			while (index > -1) {
				var line = remaining.substring(0, index);
				remaining = remaining.substring(index + 1);
				callbackFn(line);
				index = remaining.indexOf('\n');
			}

		});

		input.on('end', function() {
			if (remaining.length > 0) {
				callbackFn(remaining);
			}
		});
	},
	/**
	 * 写文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param data
	 *            数据
	 * @param callbackFn
	 *            回调函数
	 */
	write: function(filePath, data, callbackFn) {
		console.log(filePath)
		fs.writeFile(filePath, data, function(err) {
			if (err) {
				throw err;
			}

			if (callbackFn) {
				callbackFn();
			}
		});
	},

	async existsElseCreate(directory){//判断目录是否存在，若不存在则创建

		let promise =  new Promise(resolve => fs.exists(directory,function(exists){
			resolve(exists)
		}))
		await promise.then(async res => {
			if(res){
				console.log("目录存在")
			}
			if(!res){
				console.log("目录不存在,需要创建")
				let p = new Promise(resolve => {
					fs.mkdir(directory, { recursive: true }, function(err){
						resolve(err)
					})
				})
				await p.then(res => {console.log(directory,'创建成功')}).catch(err => console.log(err))
			}
		})

	}
};

module.exports = file;