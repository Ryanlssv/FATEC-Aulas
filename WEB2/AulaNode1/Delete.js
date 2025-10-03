const fs = require('fs').promises;

async function deleteFile() {
  const filePath = 'file.txt';

  try {
    
    await fs.access(filePath);

    
    await fs.unlink(filePath);
    console.log('File deleted successfully');
  } catch (err) {
    if (err.code === 'ENOENT') {
      console.log('File does not exist');
    } else {
      console.error('Error deleting file:', err);
    }
  }
}

deleteFile();
